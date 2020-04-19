package com.whu.bookstore_server.domain;

import com.baomidou.mybatisplus.annotations.TableId;

public class Ad {
    @TableId
    private String adId;

    private String name;

    private String imageUrl;

    private String clickUrl;

    private String date;

    private Integer queue;

    private Integer isAbandon;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getClickUrl() {
        return clickUrl;
    }

    public void setClickUrl(String clickUrl) {
        this.clickUrl = clickUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getQueue() {
        return queue;
    }

    public void setQueue(Integer queue) {
        this.queue = queue;
    }

    public Integer getIsAbandon() {
        return isAbandon;
    }

    public void setIsAbandon(Integer isAbandon) {
        this.isAbandon = isAbandon;
    }
}
