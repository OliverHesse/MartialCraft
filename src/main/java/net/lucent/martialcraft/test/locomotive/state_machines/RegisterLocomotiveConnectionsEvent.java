package net.lucent.martialcraft.test.locomotive.state_machines;

import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeCondition;
import net.lucent.martialcraft.test.locomotive.MovementContext;
import net.lucent.martialcraft.test.state_machine.GenericEntityTypeStateMachineBuilder;
import net.lucent.martialcraft.test.state_machine.StateChangeConditionPosition;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.Event;

public class RegisterLocomotiveConnectionsEvent extends Event {

    private final GenericEntityTypeStateMachineBuilder<MovementContext> builder;

    public RegisterLocomotiveConnectionsEvent(GenericEntityTypeStateMachineBuilder<MovementContext> builder) {
        this.builder = builder;
    }

    public void addStateConnection(State<MovementContext> state, StateChangeCondition<MovementContext> condition, StateChangeConditionPosition<MovementContext> position){
       builder.addStateConnection(state,condition,position);

    }
    public void addStateConnection(EntityType<?> entityType,State<MovementContext> state,StateChangeCondition<MovementContext> condition,StateChangeConditionPosition<MovementContext> position){
        builder.addStateConnection(entityType,state,condition,position);
    }



    public void removeConnection(State<MovementContext> initialState,State<MovementContext> finalState){
        builder.removeConnection(initialState,finalState);
    }
    public void removeConnection(EntityType<?> entityType,State<MovementContext> initialState,State<MovementContext> finalState){
        builder.removeConnection(entityType,initialState,finalState);
    }


    public void modifyConnectionPosition(State<MovementContext> initialState,State<MovementContext> finalState,StateChangeConditionPosition<MovementContext> newPosition){
        builder.modifyConnectionPosition(initialState,finalState,newPosition);
    }
    public void modifyConnectionPosition(EntityType<?> entityType,State<MovementContext> initialState,State<MovementContext> finalState,StateChangeConditionPosition<MovementContext> newPosition){
        builder.modifyConnectionPosition(entityType,initialState,finalState,newPosition);
    }
}
