package net.lucent.martialcraft.state_machine.state_change;

import net.lucent.martialcraft.state_machine.StateInstance;

public sealed interface StateChangeResult permits StateChangeResult.Change, StateChangeResult.NoChange {

    record Change(StateInstance<?> instance) implements StateChangeResult {

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public StateInstance<?> get() {
            return instance;
        }
    }

    record NoChange() implements StateChangeResult{

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public StateInstance<?> get() {
            return null;
        }
    }

    boolean isSuccess();

    StateInstance<?> get();
}
