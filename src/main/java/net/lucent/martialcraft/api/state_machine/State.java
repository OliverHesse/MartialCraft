package net.lucent.martialcraft.api.state_machine;

import net.lucent.martialcraft.test.util.StateChangeConditionsHolder;
import net.minecraft.world.entity.LivingEntity;


public interface State<T extends StateData,S extends StateContext> {

    StateChangeConditionsHolder<S> getConditionHolder();
    T getFreshStateInstance();

    //TODO consider including info abt previous state
    void enterState(LivingEntity entity,StateData data);

    void leaveState(LivingEntity entity,StateData data);
}
