package net.lucent.martialcraft.test.locomotive.states;

import net.lucent.martialcraft.api.state_machine.StateData;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeConditionsHolder;
import net.lucent.martialcraft.test.locomotive.MovementContext;
import net.lucent.martialcraft.test.locomotive.states.data.EmptyStateData;
import net.minecraft.world.entity.LivingEntity;

public class GroundedIdleState extends GroundedMovementState<EmptyStateData>{
    @Override
    public StateChangeConditionsHolder<MovementContext> getConditionHolder() {
        return null;
    }

    @Override
    public EmptyStateData getFreshStateInstance() {
        return null;
    }

    @Override
    public void enterState(LivingEntity entity, StateData data) {

    }

    @Override
    public void leaveState(LivingEntity entity, StateData data) {

    }
}
