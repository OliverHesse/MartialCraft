package net.lucent.martialcraft.test.locomotive.states;

import net.lucent.martialcraft.MartialCraft;
import net.lucent.martialcraft.api.state_machine.StateData;
import net.lucent.martialcraft.test.locomotive.states.data.EmptyStateData;
import net.minecraft.world.entity.LivingEntity;

public class WalkingState extends GroundedMovementState {



    @Override
    public void enterState(LivingEntity entity, StateData data) {
        MartialCraft.LOGGER.debug("STARTED WALKING ("+(entity.level().isClientSide() ? "Client" : "Server")+")");
    }

    @Override
    public void leaveState(LivingEntity entity, StateData data) {
        MartialCraft.LOGGER.debug("STOPPED WALKING ("+(entity.level().isClientSide() ? "Client" : "Server")+")");
    }
}
