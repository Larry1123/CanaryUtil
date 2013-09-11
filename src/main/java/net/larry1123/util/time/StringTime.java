package net.larry1123.util.time;

import org.apache.commons.lang3.time.DateUtils;


public class StringTime {

    public static enum Part {

        DAYS("D"),
        HOUR("H"),
        MINUTES("M"),
        SECONDS("S");

        private final String ths;

        Part(String shortn) {
            ths = shortn;
        }

        public String getValue() {
            return ths;
        }

        public static Part getFromString(String type) {
            for (Part t : Part.values()) {
                if (t.getValue().toLowerCase().equals(type.toLowerCase())) {
                    return t;
                }
            }
            return HOUR;
        }
    }

    /**
     * Takes just a number or a set of numbers with a D, H, M or, S fallowing it
     * The letters can be upper or lower case
     * 15h 50m 5s
     *
     * @param string
     * @return
     */
    public static long millisecondsFromString(String string) {
        string = string.trim();
        long ret = 0;
        try {
            ret = Long.parseLong(string);
        } catch (NumberFormatException e) {
            for (String part : string.split(" ")) {
                if (part.length() >= 2) {
                    String time = part.substring(-1);
                    switch (Part.getFromString(time)) {
                        case DAYS:
                            try {
                                Long days = Long.parseLong(part.substring(0, -1));
                                ret += days * DateUtils.MILLIS_PER_DAY;
                            } catch (NumberFormatException error) {
                                // DO nothing right now
                            }
                            break;
                        case HOUR:
                            try {
                                Long hours = Long.parseLong(part.substring(0, -1));
                                ret += hours * DateUtils.MILLIS_PER_HOUR;
                            } catch (NumberFormatException error) {
                                // DO nothing right now
                            }
                            break;
                        case MINUTES:
                            try {
                                Long minutes = Long.parseLong(part.substring(0, -1));
                                ret += minutes * DateUtils.MILLIS_PER_MINUTE;
                            } catch (NumberFormatException error) {
                                // DO nothing right now
                            }
                            break;
                        case SECONDS:
                            try {
                                Long seconds = Long.parseLong(part.substring(0, -1));
                                ret += seconds * DateUtils.MILLIS_PER_SECOND;
                            } catch (NumberFormatException error) {
                                // DO nothing right now
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return ret;
    }

    public static long millisecondsFromString(String string, String string2) {
        String send = string + " " + string2;
        return millisecondsFromString(send);
    }

    public static long millisecondsFromString(String string, String string2, String string3) {
        String send = string + " " + string2 + " " + string3;
        return millisecondsFromString(send);
    }

    public static long millisecondsFromString(String[] strings) {
        String send = "";
        for (String parts : strings) {
            send += parts + " ";
        }
        return millisecondsFromString(send);
    }

}
