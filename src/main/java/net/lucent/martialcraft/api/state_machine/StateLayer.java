package net.lucent.martialcraft.api.state_machine;

import net.lucent.martialcraft.api.state_machine.state_change.StateChangeCondition;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeConditionsHolder;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeResult;
import net.minecraft.world.entity.LivingEntity;

/**
 *
 * @param <T> the context window this layer operates in
 */
public class StateLayer<T extends StateContext>{

    private final LivingEntity attachedEntity;
    private State<?,T> currentState;
    private StateData stateData;
    private final LayerStatesProvider<T> statesProvider;

    protected StateLayer(LivingEntity attachedEntity, LayerStatesProvider<T> statesProvider) {
        this.attachedEntity = attachedEntity;
        this.statesProvider = statesProvider;
    }


    public State<?,T> getState(){
        return currentState;
    }
    public StateData getStateData(){return stateData;}
    public void evaluateConditions(LivingEntity entity, T context){
        StateChangeConditionsHolder<T> conditionsHolder = currentState.getConditionHolder();
        for(StateChangeCondition<T> condition : conditionsHolder.conditions()){
            StateChangeResult<T> result = condition.result(entity,currentState,stateData,context);
            if(!result.isSuccess()) continue;
            changeState(result.getState(),result.getData());
            return;
        }
    }

    public void changeState(State<?,T> state){
        changeState(state,state.getFreshStateInstance());
    }


    public void changeState(State<?,T> state, StateData data){
        //TODO add leave and enter
        if(currentState != null) currentState.leaveState(attachedEntity,stateData);
        currentState = state;
        stateData = data;
        if(currentState != null) currentState.enterState(attachedEntity,stateData);
    }

}
