package net.lucent.martialcraft.test.locomotive;

import net.lucent.martialcraft.api.state_machine.StateContext;
import net.minecraft.world.entity.player.Input;
//TODO temp, need to update to work with both players and entities
public record MovementContext(Input input) implements StateContext {

}
