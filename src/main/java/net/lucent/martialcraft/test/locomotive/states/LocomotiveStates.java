package net.lucent.martialcraft.test.locomotive.states;

import net.lucent.martialcraft.MartialCraft;
import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.test.locomotive.MovementContext;
import net.lucent.martialcraft.test.state_machine.StateProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.Collection;
import java.util.List;

@EventBusSubscriber(modid = MartialCraft.MOD_ID)
public class LocomotiveStates implements StateProvider<MovementContext> {
    public static final ResourceKey<Registry<State<?, MovementContext>>> STATE_REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(MartialCraft.MOD_ID, "states"));

    public static final Registry<State<?,MovementContext>> STATE_REGISTRY =  new RegistryBuilder<>(STATE_REGISTRY_KEY)
            .create();

    public static final DeferredRegister<State<?,MovementContext>> STATES = DeferredRegister.create(
            LocomotiveStates.STATE_REGISTRY,
            MartialCraft.MOD_ID
    );


    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event){

        event.register(STATE_REGISTRY);
    }

     public static void register(IEventBus event){
        STATES.register(event);
    }

    @Override
    public State<?, MovementContext> getState(Identifier key) {
        return STATE_REGISTRY.getValue(key);
    }

    @Override
    public Collection<State<?, MovementContext>> getStates() {
        return STATE_REGISTRY.stream().toList();
    }
}
