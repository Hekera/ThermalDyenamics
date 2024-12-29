package cy.jdkdigital.dyenamics;

import cy.jdkdigital.dyenamics.common.cap.SwagHandler;
import cy.jdkdigital.dyenamics.core.init.*;
import net.minecraft.client.resources.model.Material;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod(Dyenamics.MOD_ID)
public class Dyenamics
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "dyenamics";
    public static final Map<String, Material> BED_MATERIAL_MAP = new HashMap<>();
    public static final Map<String, Material> SHULKER_MATERIAL_MAP = new HashMap<>();

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID);
    public static final Supplier<AttachmentType<SwagHandler>> SWAG_HANDLER = ATTACHMENT_TYPES.register("swag_handler", () -> AttachmentType.serializable(SwagHandler::new).build());

    public Dyenamics(IEventBus modEventBus, ModContainer modContainer) {
        BlockInit.register();
        ItemInit.register();
        EntityInit.register();

        BlockInit.BLOCKS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        EntityInit.ENTITIES.register(modEventBus);
        BlockEntityInit.BLOCK_ENTITY_TYPES.register(modEventBus);
        RecipeSerializerInit.RECIPE_SERIALIZERS.register(modEventBus);
        ATTACHMENT_TYPES.register(modEventBus);
    }
}