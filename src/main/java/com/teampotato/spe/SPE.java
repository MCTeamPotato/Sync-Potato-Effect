package com.teampotato.spe;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("spe")
public class SPE {
    public SPE() {
        MinecraftForge.EVENT_BUS.register(this);
    }
}
