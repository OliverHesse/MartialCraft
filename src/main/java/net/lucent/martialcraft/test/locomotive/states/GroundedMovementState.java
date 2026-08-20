package net.lucent.martialcraft.test.locomotive.states;

import io.netty.buffer.ByteBuf;
import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.StateData;
import net.lucent.martialcraft.test.locomotive.MovementContext;
import net.lucent.martialcraft.test.locomotive.states.data.EmptyStateData;


public abstract class GroundedMovementState implements State<MovementContext> {
    @Override
    public EmptyStateData createData() {
        return new EmptyStateData();
    }

    @Override
    public StateData createData(ByteBuf buf) {
        return null;
    }

    @Override
    public void encodeData(ByteBuf buf, StateData data) {

    }
}
