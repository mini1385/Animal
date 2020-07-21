package com.je1224.animal;

public class ShopInfo {
    String title;
    String contents;
    String url;

    public ShopInfo() {
    }

    public ShopInfo(String title, String contents, String url) {
        this.title = title;
        this.contents = contents;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
