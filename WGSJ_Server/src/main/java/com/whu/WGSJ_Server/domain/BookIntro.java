package com.whu.WGSJ_Server.domain;

import com.baomidou.mybatisplus.annotations.TableId;

// 书籍商品页上方商品（实物）轮播图
public class BookIntro {
    @TableId
    private String bookI;

    private String bookId;

    private String introUrl;

    public String getBookI() {
        return bookI;
    }

    public void setBookI(String bookI) {
        this.bookI = bookI;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getIntroUrl() {
        return introUrl;
    }

    public void setIntroUrl(String introUrl) {
        this.introUrl = introUrl;
    }
}
