package cy.jdkdigital.dyenamics.common.block;

import cy.jdkdigital.dyenamics.common.blockentity.DyenamicBannerBlockEntity;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DyenamicWallBannerBlock extends WallBannerBlock
{
    private final DyenamicDyeColor color;

    public DyenamicWallBannerBlock(DyenamicDyeColor color, Properties properties) {
        super(color.getAnalogue(), properties);
        this.color = color;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DyenamicBannerBlockEntity(pos, state);
    }

    public DyenamicDyeColor getDyenamicColor() {
        return color;
    }
}
