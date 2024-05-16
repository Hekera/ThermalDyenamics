package cy.jdkdigital.dyenamics.common.recipes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cy.jdkdigital.dyenamics.common.items.DyenamicDyeItem;
import cy.jdkdigital.dyenamics.core.init.RecipeSerializerInit;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import net.minecraft.Util;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.Map;

public class DyenamicFireworkStarRecipe extends CustomRecipe
{
   private static final Ingredient SHAPE_INGREDIENT = Ingredient.of(Items.FIRE_CHARGE, Items.FEATHER, Items.GOLD_NUGGET, Items.SKELETON_SKULL, Items.WITHER_SKELETON_SKULL, Items.CREEPER_HEAD, Items.PLAYER_HEAD, Items.DRAGON_HEAD, Items.ZOMBIE_HEAD, Items.PIGLIN_HEAD);
   private static final Map<Item, FireworkRocketItem.Shape> SHAPE_BY_ITEM = Util.make(Maps.newHashMap(), (map) -> {
      map.put(Items.FIRE_CHARGE, FireworkRocketItem.Shape.LARGE_BALL);
      map.put(Items.FEATHER, FireworkRocketItem.Shape.BURST);
      map.put(Items.GOLD_NUGGET, FireworkRocketItem.Shape.STAR);
      map.put(Items.SKELETON_SKULL, FireworkRocketItem.Shape.CREEPER);
      map.put(Items.WITHER_SKELETON_SKULL, FireworkRocketItem.Shape.CREEPER);
      map.put(Items.CREEPER_HEAD, FireworkRocketItem.Shape.CREEPER);
      map.put(Items.PLAYER_HEAD, FireworkRocketItem.Shape.CREEPER);
      map.put(Items.DRAGON_HEAD, FireworkRocketItem.Shape.CREEPER);
      map.put(Items.ZOMBIE_HEAD, FireworkRocketItem.Shape.CREEPER);
      map.put(Items.PIGLIN_HEAD, FireworkRocketItem.Shape.CREEPER);
   });

   public DyenamicFireworkStarRecipe(ResourceLocation pId, CraftingBookCategory pCategory) {
      super(pId, pCategory);
   }

   /**
    * Used to check if a recipe matches current crafting inventory
    */
   public boolean matches(CraftingContainer pInv, Level pLevel) {
      boolean hasShapeIngredient = false;
      boolean hasFlickerIngredient = false;
      boolean hasTrailIngredient = false;
      boolean hasGunpowder = false;
      boolean hasDye = false;

      for(int i = 0; i < pInv.getContainerSize(); ++i) {
         ItemStack itemstack = pInv.getItem(i);
         if (!itemstack.isEmpty()) {
            if (SHAPE_INGREDIENT.test(itemstack)) {
               if (hasShapeIngredient) {
                  return false;
               }

               hasShapeIngredient = true;
            } else if (itemstack.is(Tags.Items.DUSTS_GLOWSTONE)) {
               if (hasFlickerIngredient) {
                  return false;
               }

               hasFlickerIngredient = true;
            } else if (itemstack.is(Tags.Items.GEMS_DIAMOND)) {
               if (hasTrailIngredient) {
                  return false;
               }

               hasTrailIngredient = true;
            } else if (itemstack.is(Tags.Items.GUNPOWDER)) {
               if (hasGunpowder) {
                  return false;
               }

               hasGunpowder = true;
            } else {
               if (!(itemstack.is(Tags.Items.DYES))) {
                  return false;
               }

               hasDye = true;
            }
         }
      }

      return hasGunpowder && hasDye;
   }

   public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
      ItemStack result = new ItemStack(Items.FIREWORK_STAR);
      CompoundTag compoundtag = result.getOrCreateTagElement("Explosion");
      FireworkRocketItem.Shape shape = FireworkRocketItem.Shape.SMALL_BALL;
      List<Integer> list = Lists.newArrayList();

      for(int i = 0; i < pContainer.getContainerSize(); ++i) {
         ItemStack inputStack = pContainer.getItem(i);
         if (!inputStack.isEmpty()) {
            if (SHAPE_INGREDIENT.test(inputStack)) {
               shape = SHAPE_BY_ITEM.get(inputStack.getItem());
            } else if (inputStack.is(Tags.Items.DUSTS_GLOWSTONE)) {
               compoundtag.putBoolean("Flicker", true);
            } else if (inputStack.is(Tags.Items.GEMS_DIAMOND)) {
               compoundtag.putBoolean("Trail", true);
            } else if (inputStack.getItem() instanceof DyeItem dye) {
               list.add(dye.getDyeColor().getFireworkColor());
            } else if (inputStack.getItem() instanceof DyenamicDyeItem dye) {
               list.add(dye.getDyeColor().getFireworkColor());
            }
         }
      }

      compoundtag.putIntArray("Colors", list);
      compoundtag.putByte("Type", (byte)shape.getId());
      return result;
   }

   public boolean canCraftInDimensions(int pWidth, int pHeight) {
      return pWidth * pHeight >= 2;
   }

   public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
      return new ItemStack(Items.FIREWORK_STAR);
   }

   public RecipeSerializer<?> getSerializer() {
      return RecipeSerializerInit.FIREWORK_STAR.get();
   }
}