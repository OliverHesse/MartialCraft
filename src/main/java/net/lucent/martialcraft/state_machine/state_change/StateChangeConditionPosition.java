package net.lucent.martialcraft.state_machine.state_change;

import net.lucent.martialcraft.state_machine.State;
import net.lucent.martialcraft.state_machine.StateContext;

/**
 * Defines where in the execution order a condition should be placed.
 * <br>
 * for before and after, if that state is not present they are placed at the end by default
 * <br>
 * states registered under BEFORE_ALL are placed first based on registration order.
 * then AFTER_ALL,
 * <br>
 * once all of thema are in place we insert all the specific conditions
 */
public sealed interface StateChangeConditionPosition<T extends StateContext> permits  StateChangeConditionPosition.BEFORE, StateChangeConditionPosition.AFTER{


    record BEFORE<T extends StateContext>(State<?,T> state) implements StateChangeConditionPosition<T> {

        @Override
        public State<?, T> getTarget() {
            return state;
        }

        @Override
        public int getOffset() {
            return 0;
        }
    }
    record AFTER<T extends StateContext>(State<?,T> state) implements StateChangeConditionPosition<T> {

        @Override
        public State<?, T> getTarget() {
            return state;
        }

        @Override
        public int getOffset() {
            return 1;
        }
    }
    static <T extends StateContext> StateChangeConditionPosition<T> BEFORE(State<?,T> state){
        return new BEFORE<T>(state);
    }
    static <T extends StateContext> StateChangeConditionPosition<T> AFTER(State<?,T> state){
        return new AFTER<T>(state);
    }
    static <T extends StateContext> StateChangeConditionPosition<T>  BEFORE_ALL(){
        return new BEFORE<T>(null);
    }
    static <T extends StateContext> StateChangeConditionPosition<T>  AFTER_ALL(){
        return new AFTER<T>(null);
    }

    State<?,T> getTarget();
    //if you were to inert it into a list at the target, what offset is required
    int getOffset();
}
