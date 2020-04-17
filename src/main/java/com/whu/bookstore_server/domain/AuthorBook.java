package com.whu.bookstore_server.domain;

import com.baomidou.mybatisplus.annotations.TableId;

public class AuthorBook {
    @TableId
    private String abId;

    private String authorId;

    private String bookId;

    public String getAbId() {
        return abId;
    }

    public void setAbId(String abId) {
        this.abId = abId;
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
