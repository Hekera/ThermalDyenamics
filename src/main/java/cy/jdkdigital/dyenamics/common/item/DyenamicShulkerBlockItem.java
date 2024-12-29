package cy.jdkdigital.dyenamics.common.item;

import cy.jdkdigital.dyenamics.client.render.item.DyenamicShulkerBoxItemStackRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class DyenamicShulkerBlockItem extends BlockItem
{
    public DyenamicShulkerBlockItem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions()
        {
            final BlockEntityWithoutLevelRenderer myRenderer = new DyenamicShulkerBoxItemStackRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return myRenderer;
            }
        });
    }
}
