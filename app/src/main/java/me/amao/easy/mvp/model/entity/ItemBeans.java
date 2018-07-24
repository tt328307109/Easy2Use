package me.amao.easy.mvp.model.entity;

public class ItemBeans {
    public ItemBeans(String type, String name, int resId, String url) {
        this.type = type;
        this.name = name;
        this.resId = resId;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String type;
    private String name;
    private int resId;
    private String url;
}
