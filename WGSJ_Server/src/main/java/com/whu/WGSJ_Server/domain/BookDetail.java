package com.whu.WGSJ_Server.domain;

import com.baomidou.mybatisplus.annotations.TableId;

// 书籍商品详情页的书籍介绍详情，图片形式
public class BookDetail {
    @TableId
    private String bookD;

    private String bookId;

    private String detailUrl;

    public String getBookD() {
        return bookD;
    }

    public void setBookD(String bookD) {
        this.bookD = bookD;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
}
