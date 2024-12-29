package cy.jdkdigital.dyenamics.common.recipe;

import cy.jdkdigital.dyenamics.common.block.DyenamicShulkerBoxBlock;
import cy.jdkdigital.dyenamics.common.item.DyenamicDyeItem;
import cy.jdkdigital.dyenamics.core.init.RecipeSerializerInit;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;

public class DyenamicShulkerBoxColoringRecipe extends CustomRecipe
{
    public DyenamicShulkerBoxColoringRecipe(CraftingBookCategory category) {
        super(category);
    }

    public boolean matches(CraftingInput inv, Level pLevel) {
        int boxes = 0;
        int dyes = 0;

        int slots = inv.size();
        for (int i = 0; i < slots; ++i) {
            ItemStack slotStack = inv.getItem(i);
            if (!slotStack.isEmpty()) {
                Item item = slotStack.getItem();
                if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock) {
                    if (boxes >= 1) {
                        return false;
                    }
                    ++boxes;
                } else if (item instanceof DyenamicDyeItem) {
                    if (dyes >= 1) {
                        return false;
                    }
                    ++dyes;
                } else {
                    return false;
                }
            }
        }

        return dyes == 1 && boxes == 1;
    }

    @Override
    public ItemStack assemble(CraftingInput inv, HolderLookup.Provider pRegistryAccess) {
        ItemStack boxStack = ItemStack.EMPTY;
        DyenamicDyeColor color = DyenamicDyeColor.PEACH;
        int slots = inv.size();
        for (int i = 0; i < slots; ++i) {
            ItemStack slotStack = inv.getItem(i);
            if (!slotStack.isEmpty()) {
                Item item = slotStack.getItem();
                if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock) {
                    boxStack = slotStack;
                } else {
                    color = DyenamicDyeColor.getColor(slotStack);
                }
            }
        }

        ItemLike block = DyenamicShulkerBoxBlock.getDyenamicColoredItemStack(color).getItem();
        return boxStack.transmuteCopy(block, 1);
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializerInit.SHULKER.get();
    }
}
