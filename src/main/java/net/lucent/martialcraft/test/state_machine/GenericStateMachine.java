package net.lucent.martialcraft.test.state_machine;

import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.StateContext;
import net.lucent.martialcraft.api.state_machine.StateMachine;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeCondition;
import net.minecraft.resources.Identifier;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record GenericStateMachine<T extends StateContext>(StateProvider<T> provider,Map<State<?,T>,List<StateChangeCondition<T>>> connections) implements StateMachine<T> {



    @Override
    public State<?, T> getState(Identifier key) {
        return provider.getState(key);
    }

    @Override
    public Collection<State<?, T>> getStates() {
        return provider.getStates();
    }

    @Override
    public List<StateChangeCondition<T>> getStateChangeConditions(State<?, T> state) {
        return connections.getOrDefault(state,List.of());
    }
}
