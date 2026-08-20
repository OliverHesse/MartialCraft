package net.lucent.martialcraft.test.locomotive.states;

import io.netty.buffer.ByteBuf;
import net.lucent.martialcraft.MartialCraft;
import net.lucent.martialcraft.api.state_machine.StateData;
import net.lucent.martialcraft.test.locomotive.MovementContext;
import net.lucent.martialcraft.test.locomotive.states.data.EmptyStateData;
import net.minecraft.world.entity.LivingEntity;

public class GroundedIdleState extends GroundedMovementState{



    @Override
    public void enterState(LivingEntity entity, StateData data) {
        MartialCraft.LOGGER.debug("STARTED IDLING ("+(entity.level().isClientSide() ? "Client" : "Server")+")");
    }

    @Override
    public void leaveState(LivingEntity entity, StateData data) {
        MartialCraft.LOGGER.debug("STOPPED IDLING ("+(entity.level().isClientSide() ? "Client" : "Server")+")");
    }
}
