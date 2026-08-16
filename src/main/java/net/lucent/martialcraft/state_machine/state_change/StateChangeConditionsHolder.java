package net.lucent.martialcraft.state_machine.state_change;

import net.lucent.martialcraft.state_machine.State;

import java.util.*;

public  record StateChangeConditionsHolder<T extends StateChangeConditionContext>(List<StateChangeCondition<T,?>> conditions){


    public static class Builder<T extends StateChangeConditionContext>{

        private record StateChangeConditionPlacement<T extends StateChangeConditionContext>(StateChangeCondition<T,?> condition, StateChangeConditionPosition position){}
        private final Map<State<?,?>,StateChangeConditionPlacement<T>> conditions = new HashMap<>();
        private final List<State<?,?>> beforeAllStates = new ArrayList<>();
        private final List<State<?,?>> afterAllStates = new ArrayList<>();

        private final List<State<?,?>> others = new ArrayList<>();

        /**
         * adds a condition to the map, if one already exists replace it
         * @param condition the condition we want to add
         * @param position the position we wish to add it in
         */
        public void addCondition(StateChangeCondition<T,?> condition, StateChangeConditionPosition position){
            removeConditionForState(condition.getState());

            if(position == StateChangeConditionPosition.BEFORE_ALL) beforeAllStates.add(condition.getState());
            else if(position == StateChangeConditionPosition.AFTER_ALL) afterAllStates.add(condition.getState());
            else others.add(condition.getState());

            conditions.put(condition.getState(),new StateChangeConditionPlacement<T>(condition,position));
        }

        public void removeConditionForState(State<?,?> state){
            StateChangeConditionPlacement<T> placement = conditions.remove(state);
            if(placement == null) return;

            if(placement.position == StateChangeConditionPosition.BEFORE_ALL) beforeAllStates.remove(state);
            else if(placement.position == StateChangeConditionPosition.AFTER_ALL) afterAllStates.remove(state);
            else others.remove(state);
        }
        public void updateStateExecutionPosition(State<?,?> state, StateChangeConditionPosition position){
            StateChangeConditionPlacement<T> placement = conditions.get(state);
            if(placement == null) return;
            addCondition(placement.condition,position);

        }

        private void insertPlacement(StateChangeConditionPlacement<T> placement,List<StateChangeCondition<T,?>> conditionOutput){
            State<?,?> targetState = placement.position().getTarget();
            for(int i =0; i<conditionOutput.size();i++){
                StateChangeCondition<T,?> condition = conditionOutput.get(i);
                if(condition.getState() != targetState) continue;

                conditionOutput.add(i+placement.position.getOffset(),placement.condition);
                return;
            }
            conditionOutput.add(placement.condition);
        }

        public StateChangeConditionsHolder<T> build(){
            //TODO can probably be done more efficiently
            List<StateChangeCondition<T,?>> conditionOutput = new ArrayList<>();
            for(State<?,?> state : beforeAllStates) conditionOutput.add(conditions.remove(state).condition());
            for(State<?,?> state : afterAllStates) conditionOutput.add(conditions.remove(state).condition());
            for(State<?,?> state : others) insertPlacement(conditions.get(state),conditionOutput);
            return new StateChangeConditionsHolder<>(conditionOutput);
        }
    }
}
