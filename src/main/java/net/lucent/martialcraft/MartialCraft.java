package net.lucent.martialcraft;

import net.lucent.martialcraft.common.MartialCraftAttachments;
import net.lucent.martialcraft.test.locomotive.states.LocomotiveStates;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(MartialCraft.MOD_ID)
public class MartialCraft {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "martialcraft";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    public MartialCraft(IEventBus modEventBus, ModContainer modContainer) {

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        MartialCraftAttachments.register(modEventBus);
        LocomotiveStates.register(modEventBus);
    }


}
