package dev.lapisteam.webapp.discord.builders;

import dev.lapisteam.webapp.discord.models.EmbedMessage;
import org.springframework.lang.NonNull;

import java.util.Map;

public class EmbedMessageBuilder {

    public static EmbedMessageBuilder of(String title, String description){
        return new EmbedMessageBuilder(new EmbedMessage(title, description));
    }

    private final EmbedMessage embedMessage;

    private EmbedMessageBuilder(EmbedMessage embedMessage) {
        this.embedMessage = embedMessage;
    }

    public EmbedMessageBuilder withColor(String hex){
        embedMessage.setColor(hex.replaceFirst("#", ""));
        return this;
    }

    public EmbedMessageBuilder withFooter(String footer){
        embedMessage.setFooter(footer);
        return this;
    }

    public EmbedMessageBuilder addField(String name, String value){
        embedMessage.getFields().put(name, value);
        return this;
    }

    public EmbedMessageBuilder withFields(@NonNull Map<String, String> fields){
        embedMessage.setFields(fields);
        return this;
    }

    public EmbedMessageBuilder addFields(@NonNull Map<String, String> fields){
        embedMessage.getFields().putAll(fields);
        return this;
    }

    public EmbedMessage getEmbedMessage() {
        return embedMessage;
    }
}
