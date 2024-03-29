package cy.jdkdigital.dyenamics.client.render.item;

import com.mojang.blaze3d.vertex.PoseStack;
import cy.jdkdigital.dyenamics.common.blockentity.DyenamicBannerBlockEntity;
import cy.jdkdigital.dyenamics.common.blocks.DyenamicBannerBlock;
import cy.jdkdigital.dyenamics.core.init.BlockInit;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class DyenamicBannerItemStackRenderer extends BlockEntityWithoutLevelRenderer
{
    DyenamicBannerBlockEntity blockEntity = null;

    public DyenamicBannerItemStackRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource buffer, int pPackedLight, int pPackedOverlay) {
        if (blockEntity == null) {
            blockEntity = new DyenamicBannerBlockEntity(BlockPos.ZERO, BlockInit.DYED_BLOCKS.get(DyenamicDyeColor.PEACH.getSerializedName()).get("banner").get().defaultBlockState());
            blockEntity.fromItem(stack);
        }
        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            Block block = ((BlockItem) item).getBlock();
            if (block instanceof DyenamicBannerBlock bannerBlock) {
                blockEntity.setDyenamicColor(bannerBlock.getDyenamicColor());

                Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(blockEntity, poseStack, buffer, pPackedLight, pPackedOverlay);
            }
        }
    }
}
