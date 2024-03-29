package cy.jdkdigital.dyenamics.common.compat;

import cy.jdkdigital.dyenamics.Dyenamics;
import cy.jdkdigital.dyenamics.core.init.BlockInit;
import cy.jdkdigital.dyenamics.core.init.ItemInit;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

@JeiPlugin
public class DyenamicsJeiPlugin implements IModPlugin
{
    private static final ResourceLocation pluginId = new ResourceLocation(Dyenamics.MOD_ID, Dyenamics.MOD_ID);

    @Nonnull
    @Override
    public ResourceLocation getPluginUid() {
        return pluginId;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        Ingredient shulkers = Ingredient.of(ItemTags.create(new ResourceLocation(Dyenamics.MOD_ID, "shulker_boxes")));
        List<CraftingRecipe> shulkerRecipes = Arrays.stream(DyenamicDyeColor.dyenamicValues()).map(color -> {
            var shulkerBox = BlockInit.DYED_BLOCKS.get(color.getSerializedName()).get("shulker_box");
            return (CraftingRecipe) new ShapelessRecipe(new ResourceLocation(Dyenamics.MOD_ID, color.getSerializedName() + "_shulker"), "shulker", CraftingBookCategory.MISC, new ItemStack(shulkerBox.get()), NonNullList.of(Ingredient.EMPTY, shulkers, Ingredient.of(ItemInit.DYE_ITEMS.get(color + "_dye").get())));
        }).toList();
        registration.addRecipes(RecipeTypes.CRAFTING, shulkerRecipes);
    }
}
