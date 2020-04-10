package com.whu.WGSJ_Server.domain;

import com.baomidou.mybatisplus.annotations.TableId;

public class Book {
    @TableId
    private String bookId;

    private String name;

    private String intro;

    private String previewUrl;

    // 总价
    private Double priceT;

    // （当前）实际售价
    private Double priceN;

    private Double charge;

    private Double star;

    private Integer stock;

    private Integer reviewsT;

    private Integer salesT;

    private Integer salesM;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public Double getPriceT() {
        return priceT;
    }

    public void setPriceT(Double priceT) {
        this.priceT = priceT;
    }

    public Double getPriceN() {
        return priceN;
    }

    public void setPriceN(Double priceN) {
        this.priceN = priceN;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getReviewsT() {
        return reviewsT;
    }

    public void setReviewsT(Integer reviewsT) {
        this.reviewsT = reviewsT;
    }

    public Integer getSalesT() {
        return salesT;
    }

    public void setSalesT(Integer salesT) {
        this.salesT = salesT;
    }

    public Integer getSalesM() {
        return salesM;
    }

    public void setSalesM(Integer salesM) {
        this.salesM = salesM;
    }
}
