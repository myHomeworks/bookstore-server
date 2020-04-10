package com.whu.WGSJ_Server.domain;

import com.baomidou.mybatisplus.annotations.TableId;

public class PublisherBook {
    @TableId
    private String pbId;

    private String publisherId;

    private String bookId;

    public String getPbId() {
        return pbId;
    }

    public void setPbId(String pbId) {
        this.pbId = pbId;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
