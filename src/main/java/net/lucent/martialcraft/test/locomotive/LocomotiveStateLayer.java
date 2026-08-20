package net.lucent.martialcraft.test.locomotive;

import net.lucent.martialcraft.api.state_machine.State;
import net.lucent.martialcraft.api.state_machine.StateData;
import net.lucent.martialcraft.api.state_machine.StateLayer;
import net.lucent.martialcraft.api.state_machine.state_change.StateChangeResult;
import net.lucent.martialcraft.common.MartialCraftAttachments;
import net.lucent.martialcraft.test.locomotive.state_machines.EntityLocomotiveStateMachines;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import org.jspecify.annotations.Nullable;

public class LocomotiveStateLayer extends StateLayer<MovementContext> {

    //TODO add a default state
    public LocomotiveStateLayer(LivingEntity attachedEntity) {
        super(attachedEntity, EntityLocomotiveStateMachines.getStateMachine(attachedEntity.getType()), LocomotiveStates.IDLE.get());
    }

    @Override
    public void changeState(State<MovementContext> state, StateData data) {
        super.changeState(state, data);
        getEntity().syncData(MartialCraftAttachments.ENTITY_LOCOMOTIVE_STATE.get());
    }

    public static class SyncHandler implements AttachmentSyncHandler<LocomotiveStateLayer>{

        @Override
        public void write(RegistryFriendlyByteBuf buf, LocomotiveStateLayer attachment, boolean initialSync) {
            attachment.getStateMachine().encodeState(buf,attachment.getState(),attachment.getStateData());
        }

        @Override
        public @Nullable LocomotiveStateLayer read(IAttachmentHolder holder, RegistryFriendlyByteBuf buf, @Nullable LocomotiveStateLayer previousValue) {
            if(!(holder instanceof LivingEntity livingEntity)) return null;
            if(previousValue == null) previousValue = new LocomotiveStateLayer(livingEntity);

            StateChangeResult<MovementContext> result = previousValue.getStateMachine().decodeState(buf);

            previousValue.changeState(result.getState(),result.getData());

            return previousValue;
        }

    }
}
