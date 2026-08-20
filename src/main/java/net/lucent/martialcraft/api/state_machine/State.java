package net.lucent.martialcraft.api.state_machine;

import io.netty.buffer.ByteBuf;
import net.minecraft.world.entity.LivingEntity;


public interface State<T extends StateContext> {


    StateData createData();
    StateData createData(ByteBuf buf);
    void encodeData(ByteBuf buf,StateData data);

    //TODO consider including info abt previous state
    void enterState(LivingEntity entity, StateData data);

    void leaveState(LivingEntity entity,StateData data);
}
