package dev.lapisteam.webapp.discord.helpers;

public enum TimeStampFormatter {

    DEFAULT("<t:%x>"),

    SHORT_TIME("<t:%x:t>"),
    LONG_TIME("<t:%x:T>"),

    SHORT_DATE("<t:%x:d>"),
    LONG_DATE("<t:%x:D>"),

    SHORT_DATE_AND_TIME("<t:%x:f>"),
    LONG_DATE_AND_TIME("<t:%x:F>"),

    RELATIVE_TIME("<t:%s:R>");

    private final String format;


    TimeStampFormatter(String format) {
        this.format = format;
    }

    public String parse(long milliseconds){
        return String.format(format, milliseconds/1000);
    }
}
