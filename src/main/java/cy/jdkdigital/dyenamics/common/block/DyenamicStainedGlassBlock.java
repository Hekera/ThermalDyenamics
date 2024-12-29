package cy.jdkdigital.dyenamics.common.block;

import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BeaconBeamBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DyenamicStainedGlassBlock extends TransparentBlock implements BeaconBeamBlock
{
    private final DyenamicDyeColor color;

    public DyenamicStainedGlassBlock(DyenamicDyeColor colorIn, Block.Properties properties) {
        super(properties);
        this.color = colorIn;
    }

    public DyeColor getColor() {
        return this.color.getAnalogue();
    }

    @Override
    public Integer getBeaconColorMultiplier(BlockState state, LevelReader level, BlockPos pos, BlockPos beaconPos) {
        return this.color.getColorValue();
    }
}
