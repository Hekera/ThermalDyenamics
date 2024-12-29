package cy.jdkdigital.dyenamics.core.util;

import cy.jdkdigital.dyenamics.Dyenamics;
//import cy.jdkdigital.dyenamics.common.cap.DyenamicSwagProvider;
//import cy.jdkdigital.dyenamics.common.network.PacketHandler;
import cy.jdkdigital.dyenamics.common.network.SwagPacket;
import cy.jdkdigital.dyenamics.core.init.BlockInit;
import cy.jdkdigital.dyenamics.core.init.EntityInit;
import cy.jdkdigital.dyenamics.core.init.ItemInit;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Dyenamics.MOD_ID)
public class ModEventHandler
{
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        for (DyenamicDyeColor color : DyenamicDyeColor.dyenamicValues()) {
            var blocks = BlockInit.DYED_BLOCKS.get(color.getSerializedName());
            CauldronInteraction.WATER.map().put(blocks.get("banner").get().asItem(), CauldronInteraction.BANNER);
        }
    }

    @SubscribeEvent
    public static void onEntityAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(EntityInit.SHEEP.get(), Sheep.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(EntityInit.SHEEP.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (animal, worldIn, reason, pos, random) -> false, RegisterSpawnPlacementsEvent.Operation.OR);
    }

    @SubscribeEvent
    public static void tabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            ItemInit.DYE_ITEMS.forEach((s, registryObject) -> event.accept(registryObject.get()));
        }
        for (DyenamicDyeColor color : DyenamicDyeColor.dyenamicValues()) {
            var blocks = BlockInit.DYED_BLOCKS.get(color.getSerializedName());
            if (event.getTabKey().equals(CreativeModeTabs.COLORED_BLOCKS)) {
                event.accept(blocks.get("terracotta").get());
                event.accept(blocks.get("glazed_terracotta").get());
                event.accept(blocks.get("concrete").get());
                event.accept(blocks.get("concrete_powder").get());
                event.accept(blocks.get("wool").get());
                event.accept(blocks.get("stained_glass").get());
                event.accept(blocks.get("stained_glass_pane").get());
                if (ModList.get().isLoaded("thermal")) {
                    event.accept(blocks.get("rockwool").get());
                }
                event.accept(blocks.get("carpet").get());
            }
            if (event.getTabKey().equals(CreativeModeTabs.COLORED_BLOCKS) || event.getTabKey().equals(CreativeModeTabs.FUNCTIONAL_BLOCKS)) {
                event.accept(blocks.get("candle").get());
                event.accept(blocks.get("bed").get());
                event.accept(blocks.get("shulker_box").get());
                event.accept(blocks.get("banner").get());
            }
        }
    }

    @SubscribeEvent
    public static void payloadHandler(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Dyenamics.MOD_ID).versioned("1").optional();
        registrar.playToClient(
                SwagPacket.TYPE,
                SwagPacket.STREAM_CODEC,
                new DirectionalPayloadHandler<>(
                        SwagPacket::clientHandle,
                        SwagPacket::serverHandle
                )
        );
    }
}
