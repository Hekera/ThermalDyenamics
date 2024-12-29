package cy.jdkdigital.dyenamics.common.network;

import cy.jdkdigital.dyenamics.Dyenamics;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SwagPacket(int entityID, CompoundTag data) implements CustomPacketPayload
{
    public static final Type<SwagPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Dyenamics.MOD_ID, "swag_packet"));

    public static final StreamCodec<FriendlyByteBuf, SwagPacket> STREAM_CODEC = CustomPacketPayload.codec(SwagPacket::write, SwagPacket::new);

    private SwagPacket(FriendlyByteBuf buffer) {
        this(buffer.readInt(), ByteBufCodecs.fromCodec(CompoundTag.CODEC).decode(buffer));
    }

    private void write(FriendlyByteBuf buffer) {
        buffer.writeInt(this.entityID);
        ByteBufCodecs.fromCodec(CompoundTag.CODEC).encode(buffer, data);
    }

    public static void clientHandle(final SwagPacket data, final IPayloadContext context) {
        ClientLevel level = Minecraft.getInstance().level;

        if (level != null) {
            Entity entity = level.getEntity(data.entityID);

            if (entity != null) {
                entity.getData(Dyenamics.SWAG_HANDLER).deserializeNBT(level.registryAccess(), data.data());
            }
        }
    }

    public static void serverHandle(final SwagPacket data, final IPayloadContext context) {
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
