package net.lucent.martialcraft.test.locomotive;

import net.lucent.martialcraft.api.state_machine.StateLayer;
import net.lucent.martialcraft.test.state_machine.GenericStateMachine;
import net.minecraft.world.entity.LivingEntity;

public class LocomotiveStateLayer extends StateLayer<MovementContext> {

    //TODO add a default state
    public LocomotiveStateLayer(LivingEntity attachedEntity) {
        super(attachedEntity,null);
    }
}
