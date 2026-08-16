package net.lucent.martialcraft.state_machine;

import net.lucent.martialcraft.state_machine.state_change.StateChangeConditionContext;

/**
 *
 * @param <T> the context window this expects
 * @param <S> the base state that accepts the context window
 */
public class StateLayer<T extends StateChangeConditionContext,S extends State<?,T>>{
}
