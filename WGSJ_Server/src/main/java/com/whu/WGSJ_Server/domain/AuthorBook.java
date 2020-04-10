package com.whu.WGSJ_Server.domain;

import com.baomidou.mybatisplus.annotations.TableId;

public class AuthorBook {
    @TableId
    private String adId;

    private String authorId;

    private String bookId;

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
