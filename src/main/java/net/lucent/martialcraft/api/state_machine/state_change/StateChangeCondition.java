package net.lucent.martialcraft.api.state_machine.state_change;

import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.StateContext;
import net.lucent.martialcraft.api.state_machine.StateData;
import net.minecraft.world.entity.LivingEntity;

/**
 * Takes in a context window, and if true we change to the state
 *
 */
public interface StateChangeCondition<T extends StateContext> {

    State<T> getState();

    StateChangeResult<T> result(LivingEntity entity, State<T> currentState, StateData stateData, T context);


}
