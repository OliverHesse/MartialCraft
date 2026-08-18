package net.lucent.martialcraft.api.state_machine.state_change;

import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.StateContext;

import java.util.*;

public  record StateChangeConditionsHolder<T extends StateContext>(List<StateChangeCondition<T>> conditions){


    public static class Builder<T extends StateContext>{

        private record StateChangeConditionPlacement<T extends StateContext>(StateChangeCondition<T> condition, StateChangeConditionPosition<T> position){}
        private final Map<State<?,T>,StateChangeConditionPlacement<T>> conditions = new HashMap<>();
        private final List<State<?,T>> beforeAllStates = new ArrayList<>();
        private final List<State<?,T>> afterAllStates = new ArrayList<>();

        private final List<State<?,T>> others = new ArrayList<>();

        /**
         * adds a condition to the map, if one already exists replace it
         * @param condition the condition we want to add
         * @param position the position we wish to add it in
         */
        public void addCondition(StateChangeCondition<T> condition, StateChangeConditionPosition<T> position){
            removeConditionForState(condition.getState());

            if(position.equals(StateChangeConditionPosition.BEFORE_ALL())) beforeAllStates.add(condition.getState());
            else if(position.equals(StateChangeConditionPosition.AFTER_ALL())) afterAllStates.add(condition.getState());
            else others.add(condition.getState());

            conditions.put(condition.getState(),new StateChangeConditionPlacement<T>(condition,position));
        }

        public void removeConditionForState(State<?,?> state){
            StateChangeConditionPlacement<T> placement = conditions.remove(state);
            if(placement == null) return;

            if(placement.position.equals(StateChangeConditionPosition.BEFORE_ALL())) beforeAllStates.remove(placement.condition().getState());
            else if(placement.position.equals(StateChangeConditionPosition.AFTER_ALL())) afterAllStates.remove(placement.condition().getState());
            else others.remove(state);
        }
        public void updateStateExecutionPosition(State<?,T> state, StateChangeConditionPosition<T> position){
            StateChangeConditionPlacement<T> placement = conditions.get(state);
            if(placement == null) return;
            addCondition(placement.condition,position);

        }

        private void insertPlacement(StateChangeConditionPlacement<T> placement,List<StateChangeCondition<T>> conditionOutput){
            State<?,T> targetState = placement.position().getTarget();
            for(int i =0; i<conditionOutput.size();i++){
                StateChangeCondition<T> condition = conditionOutput.get(i);
                if(condition.getState() != targetState) continue;

                conditionOutput.add(i+placement.position.getOffset(),placement.condition);
                return;
            }
            conditionOutput.add(placement.condition);
        }

        public StateChangeConditionsHolder<T> build(){
            //TODO can probably be done more efficiently
            List<StateChangeCondition<T>> conditionOutput = new ArrayList<>();
            for(State<?,T> state : beforeAllStates) conditionOutput.add(conditions.remove(state).condition());
            for(State<?,T> state : afterAllStates) conditionOutput.add(conditions.remove(state).condition());
            for(State<?,T> state : others) insertPlacement(conditions.get(state),conditionOutput);
            return new StateChangeConditionsHolder<>(conditionOutput);
        }
    }
}
