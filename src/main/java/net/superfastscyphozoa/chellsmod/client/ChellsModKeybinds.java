package net.superfastscyphozoa.chellsmod.client;

import net.legacyfabric.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.legacyfabric.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;

public class ChellsModKeybinds {
    public static KeyBinding sprint;

    public static void RegisterKeybinds(){
        sprint = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.chellsmod.sprint", Keyboard.KEY_LSHIFT));
    }
}
