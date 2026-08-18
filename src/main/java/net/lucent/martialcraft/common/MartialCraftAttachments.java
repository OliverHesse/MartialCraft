package net.lucent.martialcraft.common;

import net.lucent.martialcraft.MartialCraft;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class MartialCraftAttachments {
    private static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MartialCraft.MOD_ID);
    public static final Supplier<AttachmentType<EntityState>> ENTITY_STATE = ATTACHMENT_TYPES.register(
            "entity_state", () -> AttachmentType.builder(holder -> new EntityState((LivingEntity) holder))
                    .build()
    );
    public static void register(IEventBus bus){
        ATTACHMENT_TYPES.register(bus);
    }
}
