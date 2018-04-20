package bean;

import java.io.Serializable;

public class userBean implements Serializable {

    private int userId;
    private String sex;
    private String username;
    private String email;
    private String iconPath;
    private String kindIds;

    public String getKindIds() {
        return kindIds;
    }

    public void setKindIds(String kindIds) {
        this.kindIds = kindIds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
