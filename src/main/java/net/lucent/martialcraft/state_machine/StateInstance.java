package net.lucent.martialcraft.state_machine;

public interface StateInstance<T extends State<?,?>> {

    T getState();
}
