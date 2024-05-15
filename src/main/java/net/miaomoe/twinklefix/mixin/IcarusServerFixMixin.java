package net.miaomoe.twinklefix.mixin;

import dev.cammiescorner.icarus.core.util.IcarusHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IcarusHelper.class)
public abstract class IcarusServerFixMixin {
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @Inject(method = "applySpeed", at = @At("HEAD"), cancellable = true, remap = false)
    private static void applySpeedFix(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        ci.cancel();
    }
}
