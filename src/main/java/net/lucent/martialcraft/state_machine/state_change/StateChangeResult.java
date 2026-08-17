package net.lucent.martialcraft.state_machine.state_change;

import net.lucent.martialcraft.state_machine.State;
import net.lucent.martialcraft.state_machine.StateContext;
import net.lucent.martialcraft.state_machine.StateData;

public sealed interface StateChangeResult<T extends StateContext> permits StateChangeResult.Change, StateChangeResult.NoChange {

    record Change<T extends StateContext>(State<?,T> state, StateData instance) implements StateChangeResult<T>{

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public State<?, T> getState() {
            return state;
        }

        @Override
        public StateData getData() {
            return instance;
        }
    }

    record NoChange<T extends StateContext>() implements StateChangeResult<T>{

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public State<?, T> getState() {
            return null;
        }

        @Override
        public StateData getData() {
            return null;
        }
    }

    boolean isSuccess();

    State<?,T> getState();
    StateData getData();
}
