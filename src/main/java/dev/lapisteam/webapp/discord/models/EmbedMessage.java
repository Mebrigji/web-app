package dev.lapisteam.webapp.discord.models;

import java.util.LinkedHashMap;
import java.util.Map;

public class EmbedMessage {

    private final String title, description;

    private String footer;
    private String color;

    private Map<String, String> fields = new LinkedHashMap<>();

    public EmbedMessage(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public void setFields(Map<String, String> fields) {
        this.fields = fields;
    }
}
