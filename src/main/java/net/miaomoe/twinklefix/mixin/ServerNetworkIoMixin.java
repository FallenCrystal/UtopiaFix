package net.miaomoe.twinklefix.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.miaomoe.twinklefix.util.MessageUtil;
import net.minecraft.server.ServerNetworkIo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ServerNetworkIo.class)
public abstract class ServerNetworkIoMixin {

    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;literal(Ljava/lang/String;)Lnet/minecraft/text/MutableText;"))
    private String modifyKickMessage(String string, @Local Exception exception) {
        return MessageUtil.toString(exception, string);
    }
}
