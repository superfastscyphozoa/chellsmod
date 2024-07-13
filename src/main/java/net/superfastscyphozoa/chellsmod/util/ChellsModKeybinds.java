package net.superfastscyphozoa.chellsmod.util;

import net.legacyfabric.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;

public class ChellsModKeybinds {

    public static void RegisterKeybinds(){
        KeyBinding sprint = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.chellsmod.sprint", Keyboard.KEY_LSHIFT));
    }
}
