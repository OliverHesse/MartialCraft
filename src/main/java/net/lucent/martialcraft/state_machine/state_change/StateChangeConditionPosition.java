package net.lucent.martialcraft.state_machine.state_change;

import net.lucent.martialcraft.state_machine.State;

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
public sealed interface StateChangeConditionPosition permits  StateChangeConditionPosition.BEFORE, StateChangeConditionPosition.AFTER{
    StateChangeConditionPosition BEFORE_ALL = new BEFORE(null);
    StateChangeConditionPosition AFTER_ALL = new AFTER(null);

    record BEFORE(State<?,?> state) implements StateChangeConditionPosition {

    }
    record AFTER(State<?,?> state) implements StateChangeConditionPosition {

    }
    static StateChangeConditionPosition BEFORE(State<?,?> state){
        return new BEFORE(state);
    }
    static StateChangeConditionPosition AFTER(State<?,?> state){
        return new AFTER(state);
    }
}
