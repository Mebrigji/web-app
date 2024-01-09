package dev.lapisteam.webapp.objects.itemshop;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Offer {

    private UUID uuid;

    private String title, img;

    private Map<String, String> extraTitle = new HashMap<>();

    private Map<String, String> description = new HashMap<>();

    private boolean canSell;

    public Offer(String title) {
        this.uuid = UUID.randomUUID();
        this.title = title;
    }

    public Map<String, String> getExtraTitle() {
        return extraTitle;
    }

    public void setExtraTitle(Map<String, String> extraTitle) {
        this.extraTitle = extraTitle;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Map<String, String> getDescription() {
        return description;
    }

    public void setDescription(Map<String, String> description) {
        this.description = description;
    }

    public void addLine(String description, String style){
        this.description.put(description, style);
    }

    public boolean isCanSell() {
        return canSell;
    }

    public void setCanSell(boolean canSell) {
        this.canSell = canSell;
    }
}
