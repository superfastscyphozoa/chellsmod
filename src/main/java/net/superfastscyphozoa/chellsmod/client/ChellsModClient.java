package net.superfastscyphozoa.chellsmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.legacyfabric.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ChellsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChellsModKeybinds.RegisterKeybinds();

        System.out.println("client");
    }
}
