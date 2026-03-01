package net.superfastscyphozoa.chellsmod;

import net.fabricmc.api.ModInitializer;
import net.superfastscyphozoa.chellsmod.registry.*;
import net.superfastscyphozoa.chellsmod.worldgen.ChellsmodWorldgen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chellsmod implements ModInitializer {
    public static final String MOD_ID = "chellsmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        RegisterItemGroups.initChellsmodItemGroups();

        RegisterItems.initChellsmodItems();
        RegisterBlocks.initChellsmodBlocks();

        RegisterEntities.initChellsmodEntities();
        RegisterMobEffects.initChellsmodMobEffects();

        RegisterDamageTypes.initChellsmodDamageTypes();

        ChellsmodWorldgen.modifyBiomes();

        Chellsmod.LOGGER.info("Hi! Love ya <3");
    }
}
