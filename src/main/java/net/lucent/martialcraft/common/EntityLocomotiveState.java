package net.lucent.martialcraft.common;

import net.lucent.martialcraft.api.state_machine.StateLayer;
import net.lucent.martialcraft.test.locomotive.LocomotiveStateLayer;
import net.lucent.martialcraft.test.locomotive.MovementContext;
import net.minecraft.world.entity.LivingEntity;

public class EntityLocomotiveState{

    public final LivingEntity attachedEntity;
    private final StateLayer<MovementContext> locomotiveLayer;
    public EntityLocomotiveState(LivingEntity attachedEntity) {
        this.attachedEntity = attachedEntity;
        locomotiveLayer = new LocomotiveStateLayer(attachedEntity);
    }
}
