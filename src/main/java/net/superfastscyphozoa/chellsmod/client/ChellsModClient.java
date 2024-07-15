package net.superfastscyphozoa.chellsmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.legacyfabric.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;

public class ChellsModClient implements ClientModInitializer {
    public static KeyBinding fog;

    @Override
    public void onInitializeClient() {
        fog = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.chellsmod.fog", Keyboard.KEY_F, "key.categories.misc"));
    }
}
