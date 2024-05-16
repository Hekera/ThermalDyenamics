package cy.jdkdigital.dyenamics.common.recipes;

import com.google.common.collect.Lists;
import cy.jdkdigital.dyenamics.common.blocks.DyenamicShulkerBoxBlock;
import cy.jdkdigital.dyenamics.common.items.DyenamicDyeItem;
import cy.jdkdigital.dyenamics.core.init.RecipeSerializerInit;
import cy.jdkdigital.dyenamics.core.util.ColorUtil;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;

import java.util.List;

public class DyenamicArmorColoringRecipe extends CustomRecipe
{
    public DyenamicArmorColoringRecipe(ResourceLocation id, CraftingBookCategory category) {
        super(id, category);
    }

    public boolean matches(CraftingContainer pInv, Level pLevel) {
        ItemStack armorStack = ItemStack.EMPTY;
        List<ItemStack> list = Lists.newArrayList();

        for(int i = 0; i < pInv.getContainerSize(); ++i) {
            ItemStack stack = pInv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof DyeableLeatherItem) {
                    if (!armorStack.isEmpty()) {
                        return false;
                    }

                    armorStack = stack;
                } else if (stack.getItem() instanceof DyeItem || stack.getItem() instanceof DyenamicDyeItem) {
                    list.add(stack);
                } else {
                    return false;
                }
            }
        }

        return !armorStack.isEmpty() && !list.isEmpty();
    }

    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        List<Item> list = Lists.newArrayList();
        ItemStack armorItem = ItemStack.EMPTY;

        for(int i = 0; i < pContainer.getContainerSize(); ++i) {
            ItemStack stack = pContainer.getItem(i);
            if (!stack.isEmpty()) {
                Item item = stack.getItem();
                if (item instanceof DyeableLeatherItem) {
                    if (!armorItem.isEmpty()) {
                        return ItemStack.EMPTY;
                    }

                    armorItem = stack.copy();
                } else if (item instanceof DyeItem || item instanceof DyenamicDyeItem) {
                    list.add(item);
                } else {
                    return ItemStack.EMPTY;
                }
            }
        }

        return !armorItem.isEmpty() && !list.isEmpty() ? ColorUtil.dyeArmor(armorItem, list) : ItemStack.EMPTY;
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializerInit.ARMOR.get();
    }
}
