package net.superfastscyphozoa.chellsmod;

import net.fabricmc.api.ModInitializer;
import net.superfastscyphozoa.chellsmod.registry.RegisterBlocks;
import net.superfastscyphozoa.chellsmod.registry.RegisterEntities;
import net.superfastscyphozoa.chellsmod.registry.RegisterItems;
import net.superfastscyphozoa.chellsmod.utils.ItemGroups;
import net.superfastscyphozoa.chellsmod.worldgen.ChellsmodWorldgen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Chellsmod implements ModInitializer {
    public static final String MOD_ID = "chellsmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        ItemGroups.initChellsmodItemGroups();

        RegisterItems.initChellsmodItems();
        RegisterBlocks.initChellsmodBlocks();

        RegisterEntities.initChellsmodEntities();

        ChellsmodWorldgen.modifyBiomes();

        Chellsmod.LOGGER.info("Hi! Love ya <3");
    }
}
