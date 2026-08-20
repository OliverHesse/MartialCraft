package net.lucent.martialcraft.test.locomotive;

import net.lucent.martialcraft.MartialCraft;
import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.test.locomotive.states.GroundedIdleState;
import net.lucent.martialcraft.test.locomotive.states.SprintingState;
import net.lucent.martialcraft.test.locomotive.states.WalkingState;
import net.lucent.martialcraft.test.state_machine.StateProvider;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.Collection;

@EventBusSubscriber(modid = MartialCraft.MOD_ID)
public class LocomotiveStates implements StateProvider<MovementContext> {
    public static final ResourceKey<Registry<State<MovementContext>>> STATE_REGISTRY_KEY = ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(MartialCraft.MOD_ID, "states"));

    public static final Registry<State<MovementContext>> STATE_REGISTRY =  new RegistryBuilder<>(STATE_REGISTRY_KEY)
            .create();

    public static final DeferredRegister<State<MovementContext>> STATES = DeferredRegister.create(
            LocomotiveStates.STATE_REGISTRY,
            MartialCraft.MOD_ID
    );

    public static final DeferredHolder<State<MovementContext>, GroundedIdleState> IDLE = STATES.register("idle",GroundedIdleState::new);

    public static final DeferredHolder<State<MovementContext>, WalkingState> WALKING = STATES.register("walking", WalkingState::new);
    public static final DeferredHolder<State<MovementContext>, SprintingState> SPRINTING = STATES.register("sprinting", SprintingState::new);

    @SubscribeEvent
    public static void registerRegistries(NewRegistryEvent event){

        event.register(STATE_REGISTRY);
    }

     public static void register(IEventBus event){
        STATES.register(event);
    }

    @Override
    public State<MovementContext> getState(Identifier key) {
        return STATE_REGISTRY.getValue(key);
    }

    @Override
    public Identifier getKey(State<MovementContext> state) {
        return STATE_REGISTRY.getKey(state);
    }

    @Override
    public Collection<State<MovementContext>> getStates() {
        return STATE_REGISTRY.stream().toList();
    }
}
