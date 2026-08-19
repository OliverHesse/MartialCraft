package net.lucent.martialcraft.api.state_machine.state_change;

import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.StateContext;
import net.lucent.martialcraft.api.state_machine.StateData;

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

    static <T extends StateContext> StateChangeResult<T> success(State<?,T> state){
        return new Change<>(state,state.createData());
    }
    static <T extends StateContext> StateChangeResult<T> success(State<?,T> state,StateData data){
        return new Change<>(state,data);
    }

    static <T extends StateContext> StateChangeResult<T> fail(){
        return new NoChange<>();
    }

    boolean isSuccess();

    State<?,T> getState();
    StateData getData();
}
