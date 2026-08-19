package net.lucent.martialcraft.test.locomotive.states;

import net.lucent.martialcraft.MartialCraft;
import net.lucent.martialcraft.api.state_machine.StateData;
import net.lucent.martialcraft.test.locomotive.states.data.EmptyStateData;
import net.minecraft.world.entity.LivingEntity;

public class SprintingState extends GroundedMovementState<EmptyStateData> {


    @Override
    public EmptyStateData createData() {
        return new EmptyStateData();
    }

    @Override
    public void enterState(LivingEntity entity, StateData data) {
        MartialCraft.LOGGER.debug("STARTED SPRINTING");
    }

    @Override
    public void leaveState(LivingEntity entity, StateData data) {
        MartialCraft.LOGGER.debug("STOPPED SPRINTING");
    }
}