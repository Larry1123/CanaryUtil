package net.larry1123.util.api.chat;

import net.canarymod.chat.TextFormat;

import java.util.ArrayList;
import java.util.Random;

/**
 * Adds a few things I felt missing. Hope to add more soon.
 *
 * @author Larry1123
 */
public class FontTools extends TextFormat {

    private static final ArrayList<String> colorList = new ArrayList<String>();
    private static final ArrayList<String> fontList = new ArrayList<String>();

    private static final String Black = BLACK;
    private static final String Navy = DARK_BLUE;
    private static final String Green = GREEN;
    private static final String Blue = TURQUIOSE;
    private static final String Red = RED;
    private static final String Purple = PURPLE;
    private static final String Gold = ORANGE;
    private static final String LightGray = LIGHT_GRAY;
    private static final String Gray = GRAY;
    private static final String DarkPurple = BLUE;
    private static final String LightGreen = LIGHT_GREEN;
    private static final String LightBlue = CYAN;
    private static final String Rose = LIGHT_RED;
    private static final String LightPurple = PINK;
    private static final String Yellow = YELLOW;
    private static final String White = WHITE;
    private static final String Random = RANDOM;
    private static final String Bold = BOLD;
    private static final String Strike = STRIKE;
    private static final String Underline = UNDERLINED;
    private static final String Italic = ITALICS;
    private static final String Reset = RESET;
    private static final String Marker = MARKER;

    static {
        colorList.add(Black);
        colorList.add(Blue);
        colorList.add(DarkPurple);
        colorList.add(Gold);
        colorList.add(Gray);
        colorList.add(Green);
        colorList.add(LightBlue);
        colorList.add(LightGray);
        colorList.add(LightGreen);
        colorList.add(LightPurple);
        colorList.add(Navy);
        colorList.add(Purple);
        colorList.add(Red);
        colorList.add(Rose);
        colorList.add(White);
        colorList.add(Yellow);
        fontList.add(Bold);
        fontList.add(Strike);
        fontList.add(Underline);
        fontList.add(Italic);
        fontList.add(Marker);
        fontList.add(Random);
        fontList.add(Reset);
    }

    /**
     * Adds a random Color to each Char in a string.
     *
     * @param string
     * @return Randomly Colored String
     */
    public static String CharRandomColor(String string) {
        char[] str = string.toCharArray();
        String retrn = "";
        for (char car : str) {
            retrn = retrn + RandomColor() + car;
        }
        return retrn;
    }

    /**
     * Gets a RandomColor Code
     *
     * @return
     */
    public static String RandomColor() {
        Random random = new Random();
        return colorList.get(random.nextInt(colorList.size() - 1));
    }

}
