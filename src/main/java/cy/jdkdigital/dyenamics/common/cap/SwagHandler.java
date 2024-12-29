package cy.jdkdigital.dyenamics.common.cap;

import cy.jdkdigital.dyenamics.Dyenamics;
import cy.jdkdigital.dyenamics.common.network.SwagPacket;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.UnknownNullability;

public class SwagHandler implements DyenamicSwagProvider
{
    private int swag = 0;

    public SwagHandler() {
    }

    @Override
    public int getSwagId() {
        return this.swag;
    }

    public void sync(Entity entity) {
        Dyenamics.LOGGER.info("sync: " + entity);
        PacketDistributor.sendToPlayersTrackingEntityAndSelf(entity, new SwagPacket(entity.getId(), serializeNBT(entity.level().registryAccess())));
    }

    @Override
    public void setSwag(int id, Entity entity) {
        this.swag = id;
        sync(entity);
    }

    @Override
    public void removeSwag(Entity entity) {
        this.setSwag(0, entity);
    }

    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        var tag = new CompoundTag();
        tag.putInt("swag", this.swag);
        return tag;
    }

    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        this.swag = tag.getInt("swag");
    }
}
