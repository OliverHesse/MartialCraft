package net.lucent.martialcraft.test.locomotive;

import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.StateLayer;
import net.minecraft.world.entity.LivingEntity;

public class LocomotiveStateLayer extends StateLayer<MovementContext> {

    //TODO add a default state
    protected LocomotiveStateLayer(LivingEntity attachedEntity) {
        super(attachedEntity);
    }
}
