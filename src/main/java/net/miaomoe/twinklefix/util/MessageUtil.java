package net.miaomoe.twinklefix.util;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.CharacterVisitor;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageUtil {

    public static final String prefix = "&6[&eTwinkle&6] &e";

    public static void sendMessage(final @NotNull PlayerEntity player, final @NotNull String message, final boolean raw) {
        Preconditions.checkNotNull(player, "player cannot be null!");
        Preconditions.checkNotNull(message, "message cannot be null!");
        player.sendMessage(Text.literal(raw ? message : ChatColor.translate('&', prefix + message)));
    }

    public static void sendMessage(final @NotNull PlayerEntity player, final @NotNull String message) {
        sendMessage(player, message, false);
    }

    public static @NotNull String getAsString(final @NotNull Text text) {
        Preconditions.checkNotNull(text, "text cannot be null!");
        final StringBuilder builder = new StringBuilder();
        final CharacterVisitor visitor = (index, style, codePoint) -> {
            builder.appendCodePoint(codePoint);
            return true;
        };
        text.copy().asOrderedText().accept(visitor);
        return builder.toString();
    }

    private static final Set<String> safeIgnoreStack = new HashSet<>(){{
       add("net.minecraft.");
       add("java.");
       add("net.fabricmc.loader.impl.");
       add("com.google.gson.");
    }};

    public static @NotNull String toString(final @NotNull Throwable throwable, final @Nullable String prefix) {
        Preconditions.checkNotNull(throwable, "throwable cannot be null!");
        final StringBuilder builder = new StringBuilder();
        if (prefix != null) {
            builder.append(prefix).append(": ");
        }
        builder.append(throwable.getMessage());
        Throwable deep = throwable.getCause();
        Throwable last = throwable;
        while (deep != null) {
            builder.append(" : ");
            builder.append(deep.getMessage());
            deep = deep.getCause();
            if (deep != null) last = deep;
        }
        for (final StackTraceElement element : last.getStackTrace()) {
            final String className = element.getClassName();
            boolean suspicious = true;
            for (final String ignoreStack : safeIgnoreStack) {
                if (className.startsWith(ignoreStack)) {
                    suspicious = false;
                    break;
                }
            }
            if (suspicious) {
                builder
                        .append(" (Maybe at ")
                        .append(className)
                        .append("#")
                        .append(element.getMethodName())
                        .append("@")
                        .append(element.getLineNumber())
                        .append("[")
                        .append(element.getFileName())
                        .append("]")
                        .append(". See console for more information.)");
                break;
            }
        }
        return builder.toString();
    }
}
