package net.lucent.martialcraft.test.locomotive;

import net.lucent.martialcraft.MartialCraft;
import net.lucent.martialcraft.common.MartialCraftAttachments;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@EventBusSubscriber(modid = MartialCraft.MOD_ID)
public class EventHandler {

    @SubscribeEvent
    public static void onTick(ServerTickEvent.Pre tickEvent){
        for(ServerPlayer player : tickEvent.getServer().getPlayerList().getPlayers()){
            Input input = player.getLastClientInput();
            LocomotiveStateLayer layer = player.getData(MartialCraftAttachments.ENTITY_LOCOMOTIVE_STATE);

            layer.evaluateConditions(new MovementContext(input));
        }
    }
    @SubscribeEvent
    public static void onClientTIck(ClientTickEvent.Pre tickEvent){
        if(Minecraft.getInstance().player == null) return;
        //TODO update to tick through EVERY ENTITY
        LocalPlayer player = Minecraft.getInstance().player;
        Input input = player.input.keyPresses;
        LocomotiveStateLayer layer = player.getData(MartialCraftAttachments.ENTITY_LOCOMOTIVE_STATE);

        layer.evaluateConditions(new MovementContext(input));
    }
}
