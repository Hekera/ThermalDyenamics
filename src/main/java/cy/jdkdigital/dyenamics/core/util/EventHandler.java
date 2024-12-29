package cy.jdkdigital.dyenamics.core.util;

import cy.jdkdigital.dyenamics.Dyenamics;
import cy.jdkdigital.dyenamics.common.block.DyenamicCarpetBlock;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = Dyenamics.MOD_ID)
public class EventHandler
{
    @SubscribeEvent
    public static void onEntitySpawn(EntityJoinLevelEvent event) {
        // Mojang changed things, it's annoying
//        if (event.getLevel() instanceof ServerLevel && event.getEntity() instanceof Llama llama) {
//            Dyenamics.LOGGER.info("spawn llama: " + event.getEntity() + " " + llama.inventory.getItem(0));
//            llama.getBodyArmorAccess().addListener(container -> {
//                var swagHandler = llama.getData(Dyenamics.SWAG_HANDLER);
//                ItemStack swagItem = container.getItem(0);
//                Dyenamics.LOGGER.info("swagItem: " + swagItem);
//                if (swagItem.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof DyenamicCarpetBlock carpetBlock) {
//                    Dyenamics.LOGGER.info("setSwag: " + carpetBlock.getDyenamicColor().getId() + " " + llama);
//                    swagHandler.setSwag(carpetBlock.getDyenamicColor().getId(), llama);
//                } else {
//                    swagHandler.removeSwag(llama);
//                }
//            });
//        }
    }
}
