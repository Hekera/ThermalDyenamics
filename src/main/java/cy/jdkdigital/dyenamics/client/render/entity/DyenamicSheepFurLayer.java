package cy.jdkdigital.dyenamics.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import cy.jdkdigital.dyenamics.common.entity.DyenamicSheep;
import cy.jdkdigital.dyenamics.core.util.DyenamicDyeColor;
import net.minecraft.client.model.SheepFurModel;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.item.DyeColor;

public class DyenamicSheepFurLayer extends RenderLayer<DyenamicSheep, SheepModel<DyenamicSheep>>
{
    protected static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/entity/sheep/sheep_fur.png");
    protected final SheepFurModel<DyenamicSheep> sheepModel;

    public DyenamicSheepFurLayer(RenderLayerParent<DyenamicSheep, SheepModel<DyenamicSheep>> rendererIn, EntityModelSet modelSet) {
        super(rendererIn);
        this.sheepModel = new SheepFurModel<>(modelSet.bakeLayer(ModelLayers.SHEEP_FUR));
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn, DyenamicSheep sheep, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!sheep.isSheared() && !sheep.isInvisible()) {
            int i = -1;
            if (sheep.hasCustomName() && "jeb_".equals(sheep.getName().getString())) {
                int j = 25;
                int k = sheep.tickCount / 25 + sheep.getId();
                int l = DyeColor.values().length;
                int i1 = k % l;
                int j1 = (k + 1) % l;
                float f = ((float)(sheep.tickCount % 25) + partialTicks) / 25.0F;
                int k1 = Sheep.getColor(DyeColor.byId(i1));
                int l1 = Sheep.getColor(DyeColor.byId(j1));
                i = FastColor.ARGB32.lerp(f, k1, l1);
            } else {
                DyenamicDyeColor color = sheep.getDyenamicColor();
                if (color.getLightValue() > 0) {
                    int light = Math.min(color.getLightValue() * 2, 15);
                    packedLightIn = (Math.max((packedLightIn >> 20) & 15, light) << 20) | (Math.max((packedLightIn >> 4) & 15, light) << 4);
                }
                i = color.getColorValue();
            }
            coloredCutoutModelCopyLayerRender(this.getParentModel(), this.sheepModel, TEXTURE, poseStack, bufferIn, packedLightIn, sheep, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, i);
        }
    }
}
