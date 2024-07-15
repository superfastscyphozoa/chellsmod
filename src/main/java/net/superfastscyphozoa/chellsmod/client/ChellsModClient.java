package net.superfastscyphozoa.chellsmod.client;

import net.fabricmc.api.ClientModInitializer;

public class ChellsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ChellsModKeybinds.RegisterKeybinds();
        //e
    }
}
