package dev.lapisteam.webapp.objects;

public class Account {
    public Account(String nickName, String avatar, String group) {
        this.nickName = nickName;
        this.avatar = avatar;
        this.group = group;
    }

    private String nickName;
    private String avatar;
    private String group;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
