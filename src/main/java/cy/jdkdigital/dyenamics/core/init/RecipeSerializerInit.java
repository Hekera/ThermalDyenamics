package cy.jdkdigital.dyenamics.core.init;

import cy.jdkdigital.dyenamics.Dyenamics;
import cy.jdkdigital.dyenamics.common.recipes.DyenamicArmorColoringRecipe;
import cy.jdkdigital.dyenamics.common.recipes.DyenamicFireworkStarRecipe;
import cy.jdkdigital.dyenamics.common.recipes.DyenamicShulkerBoxColoringRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeSerializerInit
{
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Dyenamics.MOD_ID);

    public static final RegistryObject<SimpleCraftingRecipeSerializer<DyenamicShulkerBoxColoringRecipe>> SHULKER = RECIPE_SERIALIZERS.register("shulker_box_coloring", () -> new SimpleCraftingRecipeSerializer<>(DyenamicShulkerBoxColoringRecipe::new));
    public static final RegistryObject<SimpleCraftingRecipeSerializer<DyenamicArmorColoringRecipe>> ARMOR = RECIPE_SERIALIZERS.register("armor_coloring", () -> new SimpleCraftingRecipeSerializer<>(DyenamicArmorColoringRecipe::new));
    public static final RegistryObject<SimpleCraftingRecipeSerializer<DyenamicFireworkStarRecipe>> FIREWORK_STAR = RECIPE_SERIALIZERS.register("firework_star", () -> new SimpleCraftingRecipeSerializer<>(DyenamicFireworkStarRecipe::new));
}
