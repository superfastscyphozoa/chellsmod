package net.superfastscyphozoa.chellsmod.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.superfastscyphozoa.chellsmod.registry.RegisterEntities;

import java.util.concurrent.CompletableFuture;

public class EntityTagProvider extends FabricTagProvider.EntityTypeTagProvider {
    public EntityTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        valueLookupBuilder(EntityTypeTags.ARTHROPOD)
                .add(RegisterEntities.FLY);
    }
}
