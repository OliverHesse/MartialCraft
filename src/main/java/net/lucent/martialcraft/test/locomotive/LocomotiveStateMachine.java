package net.lucent.martialcraft.test.locomotive;

import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.StateMachine;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeCondition;
import net.minecraft.resources.Identifier;

import java.util.Collection;
import java.util.List;
//TODO implement in a registry of entity type->locomotive state machine
public class LocomotiveStateMachine implements StateMachine<MovementContext> {
    @Override
    public State<?, MovementContext> getState(Identifier key) {
        return null;
    }

    @Override
    public Collection<State<?, MovementContext>> getStates() {
        return List.of();
    }

    @Override
    public List<StateChangeCondition<MovementContext>> getStateChangeConditions(State<?, MovementContext> state) {
        return List.of();
    }
}
