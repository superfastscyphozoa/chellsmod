package net.superfastscyphozoa.chellsmod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.biome.BeachBiome;
import net.minecraft.world.biome.Biome;
import net.superfastscyphozoa.chellsmod.util.ChellsModKeybinds;

public class ChellsMod implements ModInitializer {
	public static final String MOD_ID = "chellsmod";

	@Override
	public void onInitialize() {

		ChellsModKeybinds.RegisterKeybinds();

		System.out.println("gnashes teeth");
	}
}
