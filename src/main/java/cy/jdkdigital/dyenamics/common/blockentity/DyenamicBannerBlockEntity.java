package cy.jdkdigital.dyenamics.common.blockentity;

import cy.jdkdigital.dyenamics.common.block.DyenamicBannerBlock;
import cy.jdkdigital.dyenamics.common.block.DyenamicWallBannerBlock;
import cy.jdkdigital.dyenamics.core.init.BlockEntityInit;
import cy.jdkdigital.dyenamics.core.init.BlockInit;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class DyenamicBannerBlockEntity extends BannerBlockEntity
{
    private DyenamicDyeColor baseColor;

    public DyenamicBannerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
        if (state.getBlock() instanceof DyenamicBannerBlock dyenamicBannerBlock) {
            this.baseColor = dyenamicBannerBlock.getDyenamicColor();
        } else if (state.getBlock() instanceof DyenamicWallBannerBlock dyenamicBannerBlock) {
            this.baseColor = dyenamicBannerBlock.getDyenamicColor();
        }
    }

    @Override
    public BlockEntityType<?> getType() {
        return BlockEntityInit.BANNER.get();
    }

    public void setDyenamicColor(DyenamicDyeColor color) {
        this.baseColor = color;
    }

    public DyenamicDyeColor getDyenamicColor() {
        return baseColor;
    }

    @Override
    public ItemStack getItem() {
        ItemStack itemstack = new ItemStack(BlockInit.DYED_BLOCKS.get(baseColor.getSerializedName()).get("banner").get());
        itemstack.applyComponents(this.collectComponents());
        return itemstack;
    }
}
