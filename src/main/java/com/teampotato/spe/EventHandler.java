package com.teampotato.spe;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;

@Mod.EventBusSubscriber
public class EventHandler {
    @SubscribeEvent
    public static void changeDimension(EntityTravelToDimensionEvent event) {
        if (event.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            ArrayList<EffectInstance> effects = new ArrayList<>(player.getActivePotionEffects());
            for (int i = effects.size() - 1; i >= 0; i --) {
                player.removePotionEffect(effects.get(i).getPotion());
                player.addPotionEffect(effects.get(i));
            }
        }
    }
}
