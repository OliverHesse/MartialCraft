package net.lucent.martialcraft.api.state_machine;

/**
 * A marker interface for something that holds a context window for a state change condition.
 * each state will define what superclass of StateChangeConditionContext it uses, and StateChangeCondition can extract
 * the data itself for more readable methods
 */
public interface StateContext {
}
