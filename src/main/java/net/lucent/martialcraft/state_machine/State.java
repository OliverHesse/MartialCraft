package net.lucent.martialcraft.state_machine;

import net.lucent.martialcraft.state_machine.state_change.StateChangeCondition;
import net.lucent.martialcraft.state_machine.state_change.StateChangeConditionContext;

import java.util.List;

public interface State<T extends StateInstance<?>,S extends StateChangeConditionContext> {

    List<StateChangeCondition<S,State<?,S>>> getStateChangeConditions();

    T getFreshStateInstance();
}
