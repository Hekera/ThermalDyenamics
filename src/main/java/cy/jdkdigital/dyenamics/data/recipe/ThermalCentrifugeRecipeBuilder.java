package cy.jdkdigital.dyenamics.data.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public final class ThermalCentrifugeRecipeBuilder implements RecipeBuilder
{
    public final Advancement.Builder advancement = Advancement.Builder.recipeAdvancement();
    private final Ingredient ingredient;
    private final List<ItemStack> results;
    private final int energy;

    public ThermalCentrifugeRecipeBuilder(Ingredient ingredient, List<ItemStack> results, int energy) {
        this.ingredient = ingredient;
        this.results = results;
        this.energy = energy;
    }

    public static ThermalCentrifugeRecipeBuilder direct(Ingredient ingredient, List<ItemStack> results, int energy) {
        return new ThermalCentrifugeRecipeBuilder(ingredient, results, energy);
    }

    @Override
    public RecipeBuilder unlockedBy(String name, CriterionTriggerInstance criterion) {
        this.advancement.addCriterion(name, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return results.get(0).getItem();
    }

    @Override
    public void save(Consumer<FinishedRecipe> consumer, ResourceLocation id) {
        consumer.accept(new Result(id, ingredient, results, energy, this.advancement));
    }

    record Result(ResourceLocation id, Ingredient ingredient, List<ItemStack> results, int energy, Advancement.Builder advancement) implements FinishedRecipe
    {
        @Override
        public void serializeRecipeData(JsonObject json) {
            json.add("ingredient", ingredient.toJson());
            JsonArray resultArray = new JsonArray();
            results.forEach(itemStack -> {
                resultArray.add(itemToJson(itemStack));
            });
            json.add("result", resultArray);
            json.addProperty("energy", energy);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ForgeRegistries.RECIPE_SERIALIZERS.getValue(new ResourceLocation("thermal:centrifuge"));
        }

        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return this.advancement.serializeToJson();
        }

        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return id.withPrefix("recipes/" + RecipeCategory.MISC.getFolderName() + "/");
        }
    }

    public static JsonObject itemToJson(ItemStack item) {
        JsonObject json = new JsonObject();
        json.addProperty("item", ForgeRegistries.ITEMS.getKey(item.getItem()).toString());
        if (item.getCount() > 1) {
            json.addProperty("count", item.getCount());
        }

        if (item.getTag() != null) {
            json.addProperty("type", "forge:nbt");
            json.addProperty("nbt", ((JsonElement) NbtOps.INSTANCE.convertTo(JsonOps.INSTANCE, item.getTag())).toString());
        }

        return json;
    }
}
