package net.lucent.martialcraft.api.state_machine;

import net.lucent.martialcraft.api.state_machine.state_change.StateChangeCondition;
import net.minecraft.resources.Identifier;

import java.util.Collection;
import java.util.List;

public interface StateMachine<T extends StateContext> {

    State<?,T> getState(Identifier key);
    Collection<State<?,T>> getStates();

    /**
     * @param state the state from which connections are outgoing
     * @return an ordered list of change conditions outgoing from the input state
     */
    List<StateChangeCondition<T>> getStateChangeConditions(State<?,T> state);
}
