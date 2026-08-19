package net.lucent.martialcraft.test.locomotive.connections;

import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.StateData;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeCondition;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeResult;
import net.lucent.martialcraft.test.locomotive.MovementContext;
import net.lucent.martialcraft.test.locomotive.states.LocomotiveStates;
import net.minecraft.world.entity.LivingEntity;

public class IdleConnection implements StateChangeCondition<MovementContext> {
    @Override
    public State<?, MovementContext> getState() {
        return LocomotiveStates.IDLE.get();
    }

    @Override
    public StateChangeResult<MovementContext> result(LivingEntity entity, State<?, MovementContext> currentState, StateData stateData, MovementContext context) {
        return (context.input().backward() || context.input().forward() || context.input().jump() || context.input().left() || context.input().right()) ? StateChangeResult.fail() : StateChangeResult.success(LocomotiveStates.IDLE.get());
    }
}
