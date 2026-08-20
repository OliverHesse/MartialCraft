package net.lucent.martialcraft.test.state_machine;

import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.StateContext;
import net.minecraft.resources.Identifier;
import org.lwjgl.system.linux.Stat;

import java.util.Collection;

public interface StateProvider<T extends StateContext> {


    State<T> getState(Identifier key);
    Identifier getKey(State<T> state);

    Collection<State<T>> getStates();
}
