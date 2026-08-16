package net.lucent.martialcraft.common.capabilitie;

import net.lucent.martialcraft.MartialCraft;
import net.lucent.martialcraft.state_machine.EntityState;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.transfer.ResourceHandler;

public class CoreCapabilities {
    public static final EntityCapability<EntityState, Void> ENTITY_STATE =
            EntityCapability.createVoid(
                    Identifier.fromNamespaceAndPath(MartialCraft.MOD_ID, "entity_state"),
                    EntityState.class
            );
}
