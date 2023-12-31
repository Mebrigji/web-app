package dev.lapisteam.webapp.discord.helpers;

public class LinkedMessage {

    private static final String FORMAT = "[%s](%s)";

    private final String url;
    private final String content;

    public LinkedMessage(String url, String content) {
        this.url = url;
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format(FORMAT, content, url);
    }
}
