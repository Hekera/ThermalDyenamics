package cy.jdkdigital.dyenamics.core.util;

import cy.jdkdigital.dyenamics.common.item.DyenamicDyeItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

import java.util.List;

public class ColorUtil
{
    public static ItemStack dyeArmor(ItemStack pStack, List<Item> pDyes) {
        if (!pStack.is(ItemTags.DYEABLE)) {
            return ItemStack.EMPTY;
        } else {
            ItemStack itemstack = pStack.copyWithCount(1);
            int i = 0;
            int j = 0;
            int k = 0;
            int l = 0;
            int i1 = 0;
            DyedItemColor dyeditemcolor = itemstack.get(DataComponents.DYED_COLOR);
            if (dyeditemcolor != null) {
                int j1 = FastColor.ARGB32.red(dyeditemcolor.rgb());
                int k1 = FastColor.ARGB32.green(dyeditemcolor.rgb());
                int l1 = FastColor.ARGB32.blue(dyeditemcolor.rgb());
                l += Math.max(j1, Math.max(k1, l1));
                i += j1;
                j += k1;
                k += l1;
                i1++;
            }

            for (Item dyeItem : pDyes) {
                int j3;
                if (dyeItem instanceof DyeItem dye) {
                    j3 = dye.getDyeColor().getTextureDiffuseColor();
                } else if (dyeItem instanceof DyenamicDyeItem dye) {
                    j3 = dye.getDyeColor().getColorComponentValue();
                } else {
                    continue;
                }
                int i2 = FastColor.ARGB32.red(j3);
                int j2 = FastColor.ARGB32.green(j3);
                int k2 = FastColor.ARGB32.blue(j3);
                l += Math.max(i2, Math.max(j2, k2));
                i += i2;
                j += j2;
                k += k2;
                i1++;
            }

            int l2 = i / i1;
            int i3 = j / i1;
            int k3 = k / i1;
            float f = (float)l / (float)i1;
            float f1 = (float)Math.max(l2, Math.max(i3, k3));
            l2 = (int)((float)l2 * f / f1);
            i3 = (int)((float)i3 * f / f1);
            k3 = (int)((float)k3 * f / f1);
            int l3 = FastColor.ARGB32.color(0, l2, i3, k3);
            boolean flag = dyeditemcolor == null || dyeditemcolor.showInTooltip();
            itemstack.set(DataComponents.DYED_COLOR, new DyedItemColor(l3, flag));
            return itemstack;
        }
    }
}
