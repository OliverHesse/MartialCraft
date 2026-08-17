package net.lucent.martialcraft.state_machine;

import net.lucent.martialcraft.state_machine.state_change.StateChangeConditionsHolder;


public interface State<T extends StateData,S extends StateContext> {

    StateChangeConditionsHolder<S> getConditionHolder();
    T getFreshStateInstance();


}
