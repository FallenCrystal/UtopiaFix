package net.miaomoe.twinklefix.util;

import lombok.Getter;

import java.awt.*;
import java.util.Locale;
import java.util.regex.Pattern;

@Getter
@SuppressWarnings("unused")
public enum ChatColor {
    BLACK(new Color(0x000000)),
    DARK_BLUE(new Color(0x0000AA)),
    DARK_GREEN(new Color(0x00AA00)),
    DARK_AQUA(new Color(0x00AAAA)),
    DARK_RED(new Color(0xAA0000)),
    DARK_PURPLE(new Color(0xAA00AA)),
    GOLD(new Color(0xFFAA00)),
    GRAY(new Color(0xAAAAAA)),
    DARK_GRAY(new Color(0x555555)),
    BLUE(new Color(0x5555FF)),
    GREEN('a', new Color(0x55FF55)),
    AQUA('b', new Color(0x55FFFF)),
    RED('c', new Color(0xFF5555)),
    LIGHT_PURPLE('d', new Color(0xFF55FF)),
    YELLOW('e', new Color(0xFFFF55)),
    WHITE('f', new Color(0xFFFFFF)),
    MAGIC('k', "obfuscated"),
    BOLD('l', "bold"),
    STRIKETHROUGH('m', "strikethrough"),
    UNDERLINE('n', "underline"),
    ITALIC('o', "italic"),
    RESET('r', "reset");

    private final char colorChar;
    private final String colorName;
    private final Color color;

    ChatColor(final Color color) {
        this.colorChar = (char) (ordinal() + '0');
        this.colorName = name().toLowerCase(Locale.ROOT);
        this.color=color;
    }

    ChatColor(final char colorChar, final Color color) {
        this.colorChar = colorChar;
        this.colorName = name().toLowerCase(Locale.ROOT);
        this.color=color;
    }

    ChatColor(final char colorChar, final String colorName) {
        this.colorChar=colorChar;
        this.colorName=colorName;
        this.color=null;
    }

    @Override
    public String toString() {
        return String.valueOf(COLOR_CHAR) + colorChar;
    }

    @SuppressWarnings("UnnecessaryUnicodeEscape")
    public static final char COLOR_CHAR = '\u00A7';
    public static final String ALL_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx";
    public static final Pattern STRIP_COLOR_PATTERN = Pattern.compile( "(?i)" + COLOR_CHAR + "[0-9A-FK-ORX]" );


    public static String translate(char altColorChar, String textToTranslate) {
        char[] b = textToTranslate.toCharArray();
        for ( int i = 0; i < b.length - 1; i++ ) {
            if (b[i] == altColorChar && ALL_CODES.indexOf(b[i + 1]) > -1) {
                b[i] = ChatColor.COLOR_CHAR;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }

    public static String stripColor(final String input) {
        if (input == null) return null; else return STRIP_COLOR_PATTERN.matcher(input).replaceAll("");
    }

}
