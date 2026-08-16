package net.lucent.martialcraft.state_machine.state_change;

import net.lucent.martialcraft.state_machine.State;
import net.lucent.martialcraft.state_machine.StateInstance;
import net.minecraft.world.entity.LivingEntity;

/**
 * Takes in a context window, and if true we change to the state
 *
 */
public interface StateChangeCondition<T extends StateChangeConditionContext,S extends State<?,T>> {

    S getState();

    StateChangeResult  result(LivingEntity entity, StateInstance<S> stateInstance, T context);

}
