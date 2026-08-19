package net.lucent.martialcraft.test.locomotive.state_machines;

import net.lucent.martialcraft.MartialCraft;
import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeCondition;
import net.lucent.martialcraft.test.locomotive.MovementContext;
import net.lucent.martialcraft.test.locomotive.states.LocomotiveStates;
import net.lucent.martialcraft.test.state_machine.GenericEntityTypeStateMachineBuilder;
import net.lucent.martialcraft.test.state_machine.GenericStateMachine;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.common.NeoForge;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@EventBusSubscriber(modid = MartialCraft.MOD_ID)
public class EntityLocomotiveStateMachines {
    public static final Function<Map<State<?, MovementContext>, List<StateChangeCondition<MovementContext>>>,GenericStateMachine<MovementContext>>
            CONSTRUCTOR = map->new GenericStateMachine<>(new LocomotiveStates(),map);

    private static Map<EntityType<?>, GenericStateMachine<MovementContext>> stateMachines;

    public static GenericStateMachine<MovementContext> getStateMachine(EntityType<?> type){
        return stateMachines.get(type);
    }


    @SubscribeEvent
    public static void onLoadComplete(FMLLoadCompleteEvent event){
        GenericEntityTypeStateMachineBuilder<MovementContext> builder = new GenericEntityTypeStateMachineBuilder<>(CONSTRUCTOR);
        RegisterLocomotiveConnectionsEvent builderEvent = new RegisterLocomotiveConnectionsEvent(builder);

        NeoForge.EVENT_BUS.post(builderEvent);

        stateMachines = builder.build();
    }
}


