package net.lucent.martialcraft.api.state_machine;

import net.lucent.martialcraft.api.state_machine.state_change.StateChangeCondition;
import net.lucent.martialcraft.test.util.StateChangeConditionsHolder;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeResult;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;

/**
 *
 * @param <T> the context window this layer operates in
 */
public class StateLayer<T extends StateContext>{

    private final LivingEntity attachedEntity;
    private State<?,T> currentState;
    private StateData stateData;
    private final StateMachine<T> stateMachine;
    private final State<?,T> initialState;
    protected StateLayer(LivingEntity attachedEntity, StateMachine<T> stateMachine, State<?, T> initialState) {
        this.attachedEntity = attachedEntity;
        this.stateMachine = stateMachine;
        this.initialState = initialState;
        changeState(initialState);
    }


    public State<?,T> getState(){
        return currentState;
    }
    public StateData getStateData(){return stateData;}
    public void evaluateConditions(T context){
        if(currentState == null) return;
        List<StateChangeCondition<T>> conditions = stateMachine.getStateChangeConditions(currentState);
        for(StateChangeCondition<T> condition : conditions){
            StateChangeResult<T> result = condition.result(attachedEntity,currentState,stateData,context);
            if(!result.isSuccess()) continue;
            changeState(result.getState(),result.getData());
            return;
        }
    }

    public void changeState(State<?,T> state){
        changeState(state,state.createData());
    }


    public void changeState(State<?,T> state, StateData data){
        //TODO add leave and enter
        if(currentState != null) currentState.leaveState(attachedEntity,stateData);
        currentState = state;
        stateData = data;
        if(currentState != null) currentState.enterState(attachedEntity,stateData);
    }

}
