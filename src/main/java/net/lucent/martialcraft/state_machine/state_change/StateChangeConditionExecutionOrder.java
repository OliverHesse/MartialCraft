package net.lucent.martialcraft.state_machine.state_change;

import net.lucent.martialcraft.state_machine.State;

import java.util.*;

public class StateChangeConditionExecutionOrder {

    public static class Builder{

        private record StateChangeConditionPlacement(StateChangeCondition condition, StateChangeConditionPosition position){}
        private final Map<State<?,?>,StateChangeConditionPlacement> conditions = new HashMap<>();
        private final Set<State<?,?>> beforeAllStates = new HashSet<>();
        private final Set<State<?,?>> afterAllStates = new HashSet<>();

        /**
         * adds a condition to the map, if one already exists replace it
         * @param condition the condition we want to add
         * @param position the position we wish to add it in
         */
        public void addCondition(StateChangeCondition condition, StateChangeConditionPosition position){
            removeConditionForState(condition.getState());

            if(position == StateChangeConditionPosition.BEFORE_ALL) beforeAllStates.add(condition.getState());
            else if(position == StateChangeConditionPosition.AFTER_ALL) afterAllStates.add(condition.getState());


            conditions.put(condition.getState(),new StateChangeConditionPlacement(condition,position));
        }

        public void removeConditionForState(State<?,?> state){
            StateChangeConditionPlacement placement = conditions.remove(state);
            if(placement == null) return;

            if(placement.position == StateChangeConditionPosition.BEFORE_ALL) beforeAllStates.remove(state);
            else if(placement.position == StateChangeConditionPosition.AFTER_ALL) afterAllStates.add(state);

        }
        public void updateStateExecutionPosition(State<?,?> state, StateChangeConditionPosition position){
            StateChangeConditionPlacement placement = conditions.get(state);
            if(placement == null) return;
            addCondition(placement.condition,position);

        }

        public StateChangeConditionExecutionOrder build(){
            //TODO
            return null;
        }
    }
}
