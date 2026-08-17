package net.lucent.martialcraft.test.locomotive.states;

import net.lucent.martialcraft.state_machine.State;
import net.lucent.martialcraft.state_machine.StateData;
import net.lucent.martialcraft.test.locomotive.MovementContext;



public abstract class GroundedMovementState<T extends StateData<S>,S extends MovementContext> implements State<T,S> {

}
