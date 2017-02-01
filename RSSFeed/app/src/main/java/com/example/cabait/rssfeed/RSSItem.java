package com.example.cabait.rssfeed;

/**
 * Created by Caspar on 01.02.2017.
 */

public class RSSItem {
    private String title;
    private String desc;
    private String link;

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getLink() {
        return link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
