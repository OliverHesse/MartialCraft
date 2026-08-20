package net.lucent.martialcraft.api.state_machine;

import io.netty.buffer.ByteBuf;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeCondition;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeResult;
import net.minecraft.resources.Identifier;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

public interface StateMachine<T extends StateContext> {

    State<T> getState(Identifier key);
    Identifier getKey(State<T> state);
    Collection<State<T>> getStates();

    /**
     * @param state the state from which connections are outgoing
     * @return an ordered list of change conditions outgoing from the input state
     */
    List<StateChangeCondition<T>> getStateChangeConditions(State<T> state);


    default void encodeState(ByteBuf buf,State<T> state,StateData data) {
        buf.writeBoolean(state != null);
        if(state == null) return;
        Identifier id = getKey(state);
        buf.writeInt(id.toString().length());
        buf.writeCharSequence(id.toString(), Charset.defaultCharset());

        state.encodeData(buf,data);
    }
    default StateChangeResult<T> decodeState(ByteBuf buf){
        if(!buf.readBoolean()) return StateChangeResult.fail();

        Identifier id = Identifier.parse((String) buf.readCharSequence(buf.readInt(),Charset.defaultCharset()));

        State<T> state = getState(id);

        if(state == null) return StateChangeResult.fail();

        StateData data = state.createData(buf);

        return StateChangeResult.success(state,data);
    }
}
