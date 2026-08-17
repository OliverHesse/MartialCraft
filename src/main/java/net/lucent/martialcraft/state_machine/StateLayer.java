package net.lucent.martialcraft.state_machine;

import net.lucent.martialcraft.state_machine.state_change.StateChangeCondition;
import net.lucent.martialcraft.state_machine.state_change.StateChangeConditionsHolder;
import net.lucent.martialcraft.state_machine.state_change.StateChangeResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.saveddata.SavedData;

/**
 *
 * @param <T> the context window this layer operates in
 */
public class StateLayer<T extends StateContext>{
  ;
    private final State<?,T> defaultState;
    private State<?,T> currentState;
    private StateData stateData;

    public StateLayer(State<?, T> defaultState) {
        this.defaultState = defaultState;
        this.currentState = defaultState;
        this.stateData = defaultState.getFreshStateInstance();
    }

    public State<?,T> getState(){
        return currentState;
    }
    public StateData getStateInstance(){return stateData;}
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
        currentState = state;
        stateData = data;
    }

}
