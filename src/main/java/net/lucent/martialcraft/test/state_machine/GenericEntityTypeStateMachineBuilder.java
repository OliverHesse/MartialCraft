package net.lucent.martialcraft.test.state_machine;

import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.StateContext;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeCondition;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import org.lwjgl.system.linux.Stat;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Builds a GenericStateMachine for each entity type
 */
public class GenericEntityTypeStateMachineBuilder<T extends StateContext> {
    private HashMap<EntityType<?>, EntityTypeStateMachineBuilder<T>> builders = new HashMap<>();

    private final Function<Map<State<T>,List<StateChangeCondition<T>>>,GenericStateMachine<T>> constructor;

    public GenericEntityTypeStateMachineBuilder(Function<Map<State<T>,List<StateChangeCondition<T>>>,GenericStateMachine<T>> constructor) {
        this.constructor = constructor;
    }

    //TODO consider adding a way to block a state from receiving connections at all
    private static class EntityTypeStateMachineBuilder<T extends StateContext>{
        private record StateConnection<T extends StateContext>(State<T> initialState,State<T> finalState){}
        private record StateChangeConditionPlacement<T extends StateContext>(StateChangeCondition<T> condition, StateChangeConditionPosition<T> position){}

        private final Map<StateConnection<T>,StateChangeConditionPlacement<T>> connectionDetailMap = new HashMap<>();

        private final Map<State<T>,Set<StateConnection<T>>> stateConnections = new HashMap<>();

        private final Map<State<T>,List<State<T>>> beforeAllStatesOrder = new HashMap<>();
        private final Map<State<T>,List<State<T>>> afterAllStatesOrder = new HashMap<>();
        private final Map<State<T>,List<State<T>>> otherStatesOrder = new HashMap<>();

        //used to reduce how often we are creating them for comparison
        private final StateChangeConditionPosition<T> BEFORE_ALL = StateChangeConditionPosition.BEFORE_ALL();
        private final StateChangeConditionPosition<T> AFTER_ALL = StateChangeConditionPosition.AFTER_ALL();


        public void addStateConnection(State<T> state,StateChangeCondition<T> condition,StateChangeConditionPosition<T> position){
            StateConnection<T> connection = new StateConnection<>(state,condition.getState());
            removeConnection(connection.initialState(),connection.finalState());

            connectionDetailMap.put(connection,new StateChangeConditionPlacement<>(condition,position));
            stateConnections.computeIfAbsent(state,key->new HashSet<>()).add(connection);

            if(position.equals(BEFORE_ALL))beforeAllStatesOrder.computeIfAbsent(state,key->new ArrayList<>()).add(connection.finalState);
            else if(position.equals(AFTER_ALL))afterAllStatesOrder.computeIfAbsent(state,key->new ArrayList<>()).add(connection.finalState);
            else otherStatesOrder.computeIfAbsent(state,key->new ArrayList<>()).add(connection.finalState);

        }

        public void removeConnection(State<T> initialState,State<T> finalState){
            StateConnection<T> connection = new StateConnection<>(initialState,finalState);
            if(!connectionDetailMap.containsKey(connection)) return;
            StateChangeConditionPlacement<T> placement =  connectionDetailMap.remove(connection);

            stateConnections.computeIfPresent(connection.initialState(),(key,val)->{
                val.remove(connection);
                return val.isEmpty() ? null : val;
            });

            if(placement.position().equals(BEFORE_ALL))beforeAllStatesOrder.computeIfPresent(connection.initialState(),(key,val)->{
                val.remove(connection.finalState());
                return val.isEmpty() ? null : val;
            });
            else if(placement.position().equals(AFTER_ALL))afterAllStatesOrder.computeIfPresent(connection.initialState(),(key,val)->{
                val.remove(connection.finalState());
                return val.isEmpty() ? null : val;
            });
            else otherStatesOrder.computeIfPresent(connection.initialState(),(key,val)->{
                    val.remove(connection.finalState());
                    return val.isEmpty() ? null : val;
                });
        }

        public void modifyConnectionPosition(State<T> initialState,State<T> finalState,StateChangeConditionPosition<T> newPosition){
            StateChangeConditionPlacement<T> placement = connectionDetailMap.get(new StateConnection<>(initialState,finalState));
            if(placement == null) return;
            addStateConnection(initialState,placement.condition(),newPosition);
        }

        private void insertPlacement(StateChangeConditionPlacement<T> placement, List<StateChangeCondition<T>> conditionOutput){
            State<T> targetState = placement.position().getTarget();
            for(int i =0; i<conditionOutput.size();i++){
                StateChangeCondition<T> condition = conditionOutput.get(i);
                if(condition.getState() != targetState) continue;

                conditionOutput.add(i+placement.position.getOffset(),placement.condition);
                return;
            }
            conditionOutput.add(placement.condition);
        }

        public Map<State<T>,List<StateChangeCondition<T>>> build(){
            Map<State<T>,List<StateChangeCondition<T>>> output = new HashMap<>();
            for(State<T> state : stateConnections.keySet()){
                List<StateChangeCondition<T>> conditionOutput = new ArrayList<>();
                for(State<T> targetState : beforeAllStatesOrder.getOrDefault(state,List.of())) conditionOutput.add(connectionDetailMap.get(new StateConnection<>(state,targetState)).condition());
                for(State<T> targetState : afterAllStatesOrder.getOrDefault(state,List.of())) conditionOutput.add(connectionDetailMap.get(new StateConnection<>(state,targetState)).condition());
                for(State<T> targetState : otherStatesOrder.getOrDefault(state,List.of())) insertPlacement(connectionDetailMap.get(new StateConnection<>(state,targetState)),conditionOutput);
                output.put(state,conditionOutput);
            }
            return output;
        }

    }

    public void addStateConnection(State<T> state,StateChangeCondition<T> condition,StateChangeConditionPosition<T> position){
        for(EntityType<?> entityType : BuiltInRegistries.ENTITY_TYPE.stream().toList()){
            addStateConnection(entityType,state,condition,position);
        }

    }
    public void addStateConnection(EntityType<?> entityType,State<T> state,StateChangeCondition<T> condition,StateChangeConditionPosition<T> position){
        builders.computeIfAbsent(entityType,key->new EntityTypeStateMachineBuilder<>()).addStateConnection(state,condition,position);
    }



    public void removeConnection(State<T> initialState,State<T> finalState){
        for(EntityType<?> entityType : BuiltInRegistries.ENTITY_TYPE.stream().toList()){
            removeConnection(entityType,initialState,finalState);
        }
    }
    public void removeConnection(EntityType<?> entityType,State<T> initialState,State<T> finalState){
        builders.computeIfAbsent(entityType,key->new EntityTypeStateMachineBuilder<>()).removeConnection(initialState,finalState);
    }


    public void modifyConnectionPosition(State<T> initialState,State<T> finalState,StateChangeConditionPosition<T> newPosition){
        for(EntityType<?> entityType : BuiltInRegistries.ENTITY_TYPE.stream().toList()){
            modifyConnectionPosition(entityType,initialState,finalState,newPosition);
        }
    }
    public void modifyConnectionPosition(EntityType<?> entityType,State<T> initialState,State<T> finalState,StateChangeConditionPosition<T> newPosition){
        builders.computeIfAbsent(entityType,key->new EntityTypeStateMachineBuilder<>()).modifyConnectionPosition(initialState,finalState,newPosition);
    }


    public Map<EntityType<?>,GenericStateMachine<T>> build(){
        HashMap<EntityType<?>,GenericStateMachine<T>> finalMap = new HashMap<>();
        for(EntityType<?> key : builders.keySet()) finalMap.put(key,buildStateMachine(key));
        return finalMap;
    }
    private GenericStateMachine<T> buildStateMachine(EntityType<?> type){
        var map = builders.get(type).build();
        return constructor.apply(map);
    }
}
