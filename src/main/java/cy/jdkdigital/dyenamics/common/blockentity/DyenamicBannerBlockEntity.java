package cy.jdkdigital.dyenamics.common.blockentity;

import cy.jdkdigital.dyenamics.common.blocks.DyenamicBannerBlock;
import cy.jdkdigital.dyenamics.common.blocks.DyenamicWallBannerBlock;
import cy.jdkdigital.dyenamics.core.init.BlockEntityInit;
import cy.jdkdigital.dyenamics.core.init.BlockInit;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

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
    public @NotNull ItemStack getItem() {
        ItemStack stack = new ItemStack(BlockInit.DYED_BLOCKS.get(baseColor.getSerializedName()).get("banner").get());
        if (this.itemPatterns != null && !this.itemPatterns.isEmpty()) {
            CompoundTag $$1 = new CompoundTag();
            $$1.put("Patterns", this.itemPatterns.copy());
            BlockItem.setBlockEntityData(stack, this.getType(), $$1);
        }

        if (this.name != null) {
            stack.setHoverName(this.name);
        }

        return stack;
    }
}
