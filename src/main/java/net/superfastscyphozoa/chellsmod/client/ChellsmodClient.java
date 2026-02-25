package net.superfastscyphozoa.chellsmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.superfastscyphozoa.chellsmod.client.entity.RenderEntities;

@Environment(EnvType.CLIENT)
public class ChellsmodClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        RenderEntities.initEntityModels();

    }
}
