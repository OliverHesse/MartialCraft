package net.lucent.martialcraft.state_machine.state_change;

import net.lucent.martialcraft.state_machine.State;

/**
 * Takes in a context window, and if true we change to the state
 *
 */
public interface StateChangeCondition {

    State<?,?> getState();

    <T extends StateChangeConditionContext> StateChangeResult  result(T context);

}
