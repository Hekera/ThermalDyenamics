package cy.jdkdigital.dyenamics.common.item;

import cy.jdkdigital.dyenamics.client.render.item.DyenamicBannerItemStackRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class DyenamicBannerItem extends BannerItem
{
    public DyenamicBannerItem(Block banner, Block wallBanner, Properties properties) {
        super(banner, wallBanner, properties);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions()
        {
            final BlockEntityWithoutLevelRenderer myRenderer = new DyenamicBannerItemStackRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return myRenderer;
            }
        });
    }
}
