package net.lucent.martialcraft.test.locomotive;

import net.lucent.martialcraft.api.state_machine.StateLayer;
import net.minecraft.world.entity.LivingEntity;

public class LocomotiveStateLayerInstance extends StateLayerInstance<MovementContext>{
    protected LocomotiveStateLayerInstance(LivingEntity attachedEntity) {
        super(attachedEntity);
    }

    @Override
    protected StateLayer<MovementContext> getStateLayer() {
        return null;
    }
}
