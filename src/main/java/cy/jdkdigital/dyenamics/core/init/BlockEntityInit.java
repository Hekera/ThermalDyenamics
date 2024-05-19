package cy.jdkdigital.dyenamics.core.init;

import cy.jdkdigital.dyenamics.Dyenamics;
import cy.jdkdigital.dyenamics.common.blockentity.DyenamicBannerBlockEntity;
import cy.jdkdigital.dyenamics.common.blockentity.DyenamicBedBlockEntity;
import cy.jdkdigital.dyenamics.common.blockentity.DyenamicShulkerBoxBlockEntity;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BlockEntityInit
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Dyenamics.MOD_ID);

    public static final RegistryObject<BlockEntityType<DyenamicBedBlockEntity>> BED = BLOCK_ENTITY_TYPES.register("bed",
            () -> BlockEntityType.Builder.of(
                    DyenamicBedBlockEntity::new,
                    Arrays.stream(DyenamicDyeColor.dyenamicValues()).map(dyenamicDyeColor -> {
                        return BlockInit.DYED_BLOCKS.get(dyenamicDyeColor.getSerializedName()).get("bed").get();
                    }).toList().toArray(new Block[0])
            ).build(null));

    public static final RegistryObject<BlockEntityType<DyenamicShulkerBoxBlockEntity>> SHULKER_BOX = BLOCK_ENTITY_TYPES.register("shulker_box",
            () -> BlockEntityType.Builder.of(
                    DyenamicShulkerBoxBlockEntity::new,
                    Arrays.stream(DyenamicDyeColor.dyenamicValues()).map(dyenamicDyeColor -> {
                        return BlockInit.DYED_BLOCKS.get(dyenamicDyeColor.getSerializedName()).get("shulker_box").get();
                    }).toList().toArray(new Block[0])
            ).build(null));

    public static final RegistryObject<BlockEntityType<DyenamicBannerBlockEntity>> BANNER = BLOCK_ENTITY_TYPES.register("banner",
            () -> BlockEntityType.Builder.of(
                    DyenamicBannerBlockEntity::new,
                    Arrays.stream(DyenamicDyeColor.dyenamicValues()).flatMap(dyenamicDyeColor -> {
                        return Stream.of(BlockInit.DYED_BLOCKS.get(dyenamicDyeColor.getSerializedName()).get("banner").get(), BlockInit.DYED_BLOCKS.get(dyenamicDyeColor.getSerializedName()).get("wall_banner").get());
                    }).toList().toArray(new Block[0])
            ).build(null));
}
