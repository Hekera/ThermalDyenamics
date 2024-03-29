package cy.jdkdigital.dyenamics.common.blockentity;

import cy.jdkdigital.dyenamics.core.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class DyenamicWallBannerBlockEntity extends BannerBlockEntity
{
    public DyenamicWallBannerBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return BlockEntityInit.BANNER.get();
    }
}
