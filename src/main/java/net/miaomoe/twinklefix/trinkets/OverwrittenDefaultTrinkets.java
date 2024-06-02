package net.miaomoe.twinklefix.trinkets;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketsApi;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import net.miaomoe.twinklefix.TwinkleFix;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OverwrittenDefaultTrinkets implements Trinket {

    private static final Trinket instance = new OverwrittenDefaultTrinkets();

    @SneakyThrows
    public static void inject() {
        // private static final Trinket DEFAULT_TRINKET = new Trinket() {};
        final Field modifyTarget = TrinketsApi.class.getDeclaredField("DEFAULT_TRINKET");
        modifyTarget.setAccessible(true);
        final Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
        final Object theUnsafe = MethodHandles.privateLookupIn(unsafeClass, MethodHandles.lookup())
                .findStaticGetter(unsafeClass, "theUnsafe", unsafeClass)
                .invoke();
        final Object staticFieldBase = invoke1(unsafeClass, theUnsafe, modifyTarget, "staticFieldBase",  Object.class);
        final long staticFieldOffset = (long) invoke1(unsafeClass, theUnsafe, modifyTarget, "staticFieldOffset", long.class);
        MethodHandles
                .publicLookup()
                .findVirtual(unsafeClass, "putObject", MethodType.methodType(void.class, Object.class, long.class, Object.class))
                .invoke(theUnsafe, staticFieldBase, staticFieldOffset, instance);
        // dev.emi.trinkets.api.Trinket -> net.miaomoe.twinklefix.trinkets.OverwrittenDefaultTrinkets
        TwinkleFix.logger.info("[TwinkleFix] Modified: {}", modifyTarget.get(null));
    }

    private static Object invoke1(final Class<?> unsafeClass, final Object theUnsafe, final Field target, final String name, final Class<?> type) throws Throwable {
        return MethodHandles.publicLookup().findVirtual(unsafeClass, name, MethodType.methodType(type, Field.class)).invoke(theUnsafe, target);
    }

    // 如果模组没有特别的Trinket设置, 那么首先检查Trinket自己是否允许脱下装备. 反之如果玩家在创造模式 则无论如何都允许强制脱下装备
    @Override
    public boolean canUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return Trinket.super.canUnequip(stack, slot, entity) || (entity instanceof PlayerEntity player && player.isCreative());
    }
}
