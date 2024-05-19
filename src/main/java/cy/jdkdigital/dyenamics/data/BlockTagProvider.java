package cy.jdkdigital.dyenamics.data;

import cy.jdkdigital.dyenamics.Dyenamics;
import cy.jdkdigital.dyenamics.core.init.BlockInit;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends BlockTagsProvider
{
    public BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super(output, provider, Dyenamics.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        for (DyenamicDyeColor color: DyenamicDyeColor.dyenamicValues()) {
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                    BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("terracotta").get(),
                    BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("glazed_terracotta").get(),
                    BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("concrete").get()
            );
            tag(BlockTags.MINEABLE_WITH_SHOVEL).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("concrete_powder").get());

            tag(BlockTags.BANNERS).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("banner").get());
            tag(BlockTags.BEDS).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("bed").get());
            tag(BlockTags.CANDLES).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("candle").get());
            tag(BlockTags.CANDLE_CAKES).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("candle_cake").get());
            tag(BlockTags.WOOL).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("wool").get());
            tag(BlockTags.WOOL_CARPETS).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("carpet").get());
            tag(BlockTags.SHULKER_BOXES).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("shulker_box").get());
            tag(BlockTags.IMPERMEABLE).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("stained_glass").get());

            tag(Tags.Blocks.GLASS).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("stained_glass").get());
            tag(Tags.Blocks.STAINED_GLASS).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("stained_glass").get());
            tag(Tags.Blocks.GLASS_PANES).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("stained_glass_pane").get());
            tag(Tags.Blocks.STAINED_GLASS_PANES).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("stained_glass_pane").get());
            tag(BlockTags.create(new ResourceLocation("forge:glass/" + color.getSerializedName()))).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("stained_glass").get());
            tag(BlockTags.create(new ResourceLocation("forge:glass_panes/" + color.getSerializedName()))).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("stained_glass_pane").get());

            tag(BlockTags.create(new ResourceLocation("thermal:rockwool"))).add(BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("rockwool").get());
        }
    }

    @Override
    public String getName() {
        return "Dyenamics Block Tags Provider";
    }
}
