package net.miaomoe.twinklefix.mixin;

import io.netty.channel.ChannelHandlerContext;
import net.miaomoe.twinklefix.util.MessageUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.KeepAliveS2CPacket;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin {

    @Shadow @Final private NetworkSide side;

    @Shadow @Nullable
    public abstract Text getDisconnectReason();

    @Unique private boolean canCancelDisconnect = false;
    @Unique private boolean cancelled = false;

    @Inject(
            method = "handleDisconnection",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/listener/PacketListener;onDisconnected(Lnet/minecraft/text/Text;)V"),
            cancellable = true
    )
    void onDisconnect(CallbackInfo ci) {
        if (canCancelDisconnect) {
            ci.cancel();
            cancelled = true;
            final PlayerEntity player = MinecraftClient.getInstance().player;
            if (player != null) {
                final Text reason = getDisconnectReason();
                MessageUtil.sendMessage(player, "&c您已被迫从服务器断开连接" + (reason == null ? "" : ": "));
                if (reason != null) { MessageUtil.sendMessage(player, MessageUtil.getAsString(reason), true); }
            }
        }
        canCancelDisconnect = false;
    }

    @Inject(
            method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/packet/Packet;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/network/ClientConnection;handlePacket(Lnet/minecraft/network/packet/Packet;Lnet/minecraft/network/listener/PacketListener;)V"),
            require = 0
    )
    private void onReceivingPacket(ChannelHandlerContext channelHandlerContext, Packet<?> packet, CallbackInfo ci) {
        if (this.side != NetworkSide.CLIENTBOUND) return;
        if (packet instanceof KeepAliveS2CPacket && !canCancelDisconnect) {
            canCancelDisconnect = true;
            cancelled = false;
        }
    }

    @Inject(
            method = "handleDisconnection",
            at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;warn(Ljava/lang/String;)V", remap = false),
            cancellable = true
    )
    private void warnBlocker(CallbackInfo ci) {
        if (this.cancelled) ci.cancel();
    }

}
