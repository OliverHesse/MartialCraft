package net.lucent.martialcraft.test.locomotive.states;

import net.lucent.martialcraft.state_machine.StateInstance;

public abstract class GroundedMovementState<T extends StateInstance<? extends GroundedMovementState<T>>> extends MovementState  <T> {
}
