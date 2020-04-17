package com.whu.bookstore_server.domain;

import com.baomidou.mybatisplus.annotations.TableId;

public class Banner {
    @TableId
    private String bannerId;

    private String name;

    private String imageUrl;

    private String clickUrl;

    private String date;

    private Integer queue;

    private Integer isAbandon;

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
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
