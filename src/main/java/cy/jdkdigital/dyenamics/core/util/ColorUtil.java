package cy.jdkdigital.dyenamics.core.util;

import cy.jdkdigital.dyenamics.common.items.DyenamicDyeItem;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class ColorUtil
{
    public static ItemStack dyeArmor(ItemStack pStack, List<Item> pDyes) {
        ItemStack itemstack = ItemStack.EMPTY;
        int[] aint = new int[3];
        int i = 0;
        int j = 0;
        Item item = pStack.getItem();
        DyeableLeatherItem dyeableleatheritem = null;
        if (item instanceof DyeableLeatherItem) {
            dyeableleatheritem = (DyeableLeatherItem) item;
            itemstack = pStack.copyWithCount(1);
            if (dyeableleatheritem.hasCustomColor(pStack)) {
                int k = dyeableleatheritem.getColor(itemstack);
                float f = (float)(k >> 16 & 255) / 255.0F;
                float f1 = (float)(k >> 8 & 255) / 255.0F;
                float f2 = (float)(k & 255) / 255.0F;
                i += (int)(Math.max(f, Math.max(f1, f2)) * 255.0F);
                aint[0] += (int)(f * 255.0F);
                aint[1] += (int)(f1 * 255.0F);
                aint[2] += (int)(f2 * 255.0F);
                ++j;
            }

            for(Item dyeItem : pDyes) {
                float[] afloat;
                if (dyeItem instanceof DyeItem dye) {
                    afloat = dye.getDyeColor().getTextureDiffuseColors();
                } else if (dyeItem instanceof DyenamicDyeItem dye) {
                    afloat = dye.getDyeColor().getColorComponentValues();
                } else {
                    continue;
                }
                int i2 = (int)(afloat[0] * 255.0F);
                int l = (int)(afloat[1] * 255.0F);
                int i1 = (int)(afloat[2] * 255.0F);
                i += Math.max(i2, Math.max(l, i1));
                aint[0] += i2;
                aint[1] += l;
                aint[2] += i1;
                ++j;
            }
        }

        if (dyeableleatheritem == null) {
            return ItemStack.EMPTY;
        } else {
            int j1 = aint[0] / j;
            int k1 = aint[1] / j;
            int l1 = aint[2] / j;
            float f3 = (float)i / (float)j;
            float f4 = (float)Math.max(j1, Math.max(k1, l1));
            j1 = (int)((float)j1 * f3 / f4);
            k1 = (int)((float)k1 * f3 / f4);
            l1 = (int)((float)l1 * f3 / f4);
            int j2 = (j1 << 8) + k1;
            j2 = (j2 << 8) + l1;
            dyeableleatheritem.setColor(itemstack, j2);
            return itemstack;
        }
    }
}
