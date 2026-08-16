package net.lucent.martialcraft.state_machine;

import net.lucent.martialcraft.state_machine.state_change.StateChangeCondition;

import java.util.List;

public interface State<T extends StateChangeCondition, V extends StateInstance> {

    List<T> getStateChangeConditions();

    V getFreshStateInstance();
}
