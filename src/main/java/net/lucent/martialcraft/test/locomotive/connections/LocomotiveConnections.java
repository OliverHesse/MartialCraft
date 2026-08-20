package net.lucent.martialcraft.test.locomotive.connections;

import net.lucent.martialcraft.MartialCraft;
import net.lucent.martialcraft.test.locomotive.state_machines.RegisterLocomotiveConnectionsEvent;
import net.lucent.martialcraft.test.locomotive.LocomotiveStates;
import net.lucent.martialcraft.test.state_machine.StateChangeConditionPosition;
import net.minecraft.world.entity.EntityTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = MartialCraft.MOD_ID)
public class LocomotiveConnections {

    //TODO update connection to use a static instance
    @SubscribeEvent
    public static void registerConnections(RegisterLocomotiveConnectionsEvent event){
        event.addStateConnection(EntityTypes.PLAYER,LocomotiveStates.WALKING.get(),new IdleConnection(), StateChangeConditionPosition.BEFORE_ALL());
        event.addStateConnection(EntityTypes.PLAYER,LocomotiveStates.WALKING.get(),new SprintingCondition(), StateChangeConditionPosition.BEFORE_ALL());


        event.addStateConnection(EntityTypes.PLAYER,LocomotiveStates.SPRINTING.get(),new IdleConnection(), StateChangeConditionPosition.BEFORE_ALL());
        event.addStateConnection(EntityTypes.PLAYER,LocomotiveStates.SPRINTING.get(),new WalkingConnection(), StateChangeConditionPosition.BEFORE_ALL());


        event.addStateConnection(EntityTypes.PLAYER,LocomotiveStates.IDLE.get(),new WalkingConnection(),StateChangeConditionPosition.BEFORE_ALL());
        event.addStateConnection(EntityTypes.PLAYER,LocomotiveStates.IDLE.get(),new SprintingCondition(),StateChangeConditionPosition.BEFORE_ALL());
    }
}
