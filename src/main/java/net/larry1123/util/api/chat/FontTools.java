/*
 * Copyright 2014 ElecEntertainment
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    public static final char colorChar = '\u00A7';
    private static final ArrayList<String> colorList = new ArrayList<String>();
    private static final ArrayList<String> fontList = new ArrayList<String>();
    public static final String Black = BLACK.replace("" + colorChar, "");
    public static final String Navy = DARK_BLUE.replace("" + colorChar, "");
    public static final String Green = GREEN.replace("" + colorChar, "");
    public static final String Blue = TURQUIOSE.replace("" + colorChar, "");
    public static final String Red = RED.replace("" + colorChar, "");
    public static final String Purple = PURPLE.replace("" + colorChar, "");
    public static final String Gold = ORANGE.replace("" + colorChar, "");
    public static final String LightGray = LIGHT_GRAY.replace("" + colorChar, "");
    public static final String Gray = GRAY.replace("" + colorChar, "");
    public static final String DarkPurple = BLUE.replace("" + colorChar, "");
    public static final String LightGreen = LIGHT_GREEN.replace("" + colorChar, "");
    public static final String LightBlue = CYAN.replace("" + colorChar, "");
    public static final String Rose = LIGHT_RED.replace("" + colorChar, "");
    public static final String LightPurple = PINK.replace("" + colorChar, "");
    public static final String Yellow = YELLOW.replace("" + colorChar, "");
    public static final String White = WHITE.replace("" + colorChar, "");
    public static final String Random = RANDOM.replace("" + colorChar, "");
    public static final String Bold = BOLD.replace("" + colorChar, "");
    public static final String Strike = STRIKE.replace("" + colorChar, "");
    public static final String Underline = UNDERLINED.replace("" + colorChar, "");
    public static final String Italic = ITALICS.replace("" + colorChar, "");
    public static final String Reset = RESET.replace("" + colorChar, "");

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
        fontList.add(Random);
        fontList.add(Reset);
    }

    /**
     * Adds a random color to each character in a string.
     *
     * @param string
     *
     * @return Randomly Colored String
     */
    public static String CharRandomColor(String string) {
        char[] str = string.toCharArray();
        StringBuilder ret = new StringBuilder();
        for (char car : str) {
            ret.append(RandomColor()).append(Character.toString(car));
        }
        return ret.toString();
    }

    public static String charToColorChar(char replacement, String phrase) {
        char[] charString = phrase.toCharArray();
        int index = 0;
        for (char charE : charString) {
            if (charE == replacement) {
                if (((charString.length - 1) - index + 1) >= 0) {
                    String temp = Character.toString(charString[index + 1]);
                    if (colorList.contains(temp) || fontList.contains(temp)) {
                        charString[index] = colorChar;
                    }
                }
            }
            index++;
        }
        return new String(charString);
    }

    /**
     * Gets a RandomColor Code
     *
     * @return
     */
    public static String RandomColor() {
        Random random = new Random();
        return colorChar + colorList.get(random.nextInt(colorList.size() - 1));
    }

}
