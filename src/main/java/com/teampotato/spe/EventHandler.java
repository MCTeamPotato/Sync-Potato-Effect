package com.teampotato.spe;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public static void changeDimension(EntityTravelToDimensionEvent event) {
        if (event.getEntity().world.isRemote) return;
        ResourceLocation dim = event.getDimension().getRegistryName();
        if (event.getEntity() instanceof PlayerEntity) event.getEntity().addTag("spe_" + dim.getNamespace() + "_" + dim.getPath());
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        if (player.world.isRemote) return;
        ResourceLocation dim = player.world.getDimensionKey().getRegistryName();
        if (player.removeTag("spe_" + dim.getNamespace() + "_" + dim.getPath())) {
            ArrayList<EffectInstance> effects = new ArrayList<>(player.getActivePotionEffects());
            for (int i = effects.size() - 1; i >= 0; i --) {
                player.removePotionEffect(effects.get(i).getPotion());
                player.addPotionEffect(effects.get(i));
            }
        }
    }
}
