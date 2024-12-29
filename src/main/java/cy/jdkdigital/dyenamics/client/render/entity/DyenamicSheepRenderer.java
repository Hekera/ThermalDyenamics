package cy.jdkdigital.dyenamics.client.render.entity;

import cy.jdkdigital.dyenamics.common.entity.DyenamicSheep;
import net.minecraft.client.model.SheepModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DyenamicSheepRenderer extends MobRenderer<DyenamicSheep, SheepModel<DyenamicSheep>>
{
    private static final ResourceLocation SHEARED_SHEEP_TEXTURES = ResourceLocation.withDefaultNamespace("textures/entity/sheep/sheep.png");

    public DyenamicSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new SheepModel<>(context.bakeLayer(ModelLayers.SHEEP)), 0.7F);
        this.addLayer(new DyenamicSheepFurLayer(this, context.getModelSet()));
    }

    public ResourceLocation getTextureLocation(DyenamicSheep sheep) {
        return SHEARED_SHEEP_TEXTURES;
    }
}