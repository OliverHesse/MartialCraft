package net.lucent.martialcraft.test.locomotive.states;

import net.lucent.martialcraft.state_machine.State;
import net.lucent.martialcraft.state_machine.StateInstance;
import net.lucent.martialcraft.test.locomotive.MovementContext;

public abstract class MovementState<T extends StateInstance<? extends MovementState<T>>> implements State<T, MovementContext> {
}
