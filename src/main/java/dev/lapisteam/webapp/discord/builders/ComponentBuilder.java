package dev.lapisteam.webapp.discord.builders;

import dev.lapisteam.webapp.discord.models.Component;
import dev.lapisteam.webapp.discord.models.EmbedMessage;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;

public class ComponentBuilder {

    public static ComponentBuilder empty(){
        return new ComponentBuilder();
    }

    public static ComponentBuilder of(String content, EmbedMessage... embedMessages){
        return empty().withContent(content).withEmbeds(embedMessages);
    }

    public static ComponentBuilder of(String content){
        return empty().withContent(content);
    }

    public static ComponentBuilder of(EmbedMessage... embedMessages){
        return empty().withEmbeds(embedMessages);
    }

    public static ComponentBuilder of(String content, EmbedMessageBuilder... embedMessageBuilders){
        return empty().withContent(content).withEmbeds(Arrays.stream(embedMessageBuilders).map(EmbedMessageBuilder::getEmbedMessage).toArray(EmbedMessage[]::new));
    }

    public static ComponentBuilder of(EmbedMessageBuilder... embedMessageBuilders){
        return empty().withEmbeds(Arrays.stream(embedMessageBuilders).map(EmbedMessageBuilder::getEmbedMessage).toArray(EmbedMessage[]::new));
    }

    private final Component component;

    public ComponentBuilder() {
        this.component = new Component();
    }

    public ComponentBuilder withContent(String content){
        component.setContent(content);
        return this;
    }

    public ComponentBuilder withAvatar(String url){
        component.setAvatar(url);
        return this;
    }

    public ComponentBuilder withUserName(String userName){
        component.setUserName(userName);
        return this;
    }

    public ComponentBuilder addEmbed(EmbedMessage embedMessage){
        component.getEmbedMessageList().add(embedMessage);
        return this;
    }

    public ComponentBuilder addEmbed(EmbedMessageBuilder embedMessageBuilder){
        return addEmbed(embedMessageBuilder.getEmbedMessage());
    }

    public ComponentBuilder addEmbeds(EmbedMessage... embedMessages){
        component.getEmbedMessageList().addAll(List.of(embedMessages));
        return this;
    }

    public ComponentBuilder withEmbeds(@NonNull EmbedMessage... embedMessages){
        component.setEmbedMessageList(List.of(embedMessages));
        return this;
    }

    public Component getComponent() {
        return component;
    }
}
