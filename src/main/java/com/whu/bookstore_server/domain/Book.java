package com.whu.bookstore_server.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.util.List;

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

    private Double star = 0.0;

    private Integer stock = 200; // 存货剩余

    private Integer reviewsT = 0;

    private Integer salesT = 0;

    private Integer salesM = 0;

    @TableField(exist = false)
    private List<String> authorList;

    @TableField(exist = false)
    private String publisher;

    @TableField(exist = false)
    private List<String> introList;

    @TableField(exist = false)
    private List<String> detailList;

    private String time;

    private String fullName;

    private String isbn;

    private String publishDate;

    // 印次
    private Integer yinCi;

    private Integer pageTotal;

    private Long characterTotal;

    private String kaiBen;
    // 纸张
    private String paperType;
    // 包装
    private String baoZhuang;
    // 是否套装
    private String taoZhuang;

    private String classId;

    public List<String> getIntroList() {
        return introList;
    }

    public void setIntroList(List<String> introList) {
        this.introList = introList;
    }

    public List<String> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<String> detailList) {
        this.detailList = detailList;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getYinCi() {
        return yinCi;
    }

    public void setYinCi(Integer yinCi) {
        this.yinCi = yinCi;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Long getCharacterTotal() {
        return characterTotal;
    }

    public void setCharacterTotal(Long characterTotal) {
        this.characterTotal = characterTotal;
    }

    public String getKaiBen() {
        return kaiBen;
    }

    public void setKaiBen(String kaiBen) {
        this.kaiBen = kaiBen;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getBaoZhuang() {
        return baoZhuang;
    }

    public void setBaoZhuang(String baoZhuang) {
        this.baoZhuang = baoZhuang;
    }

    public String getTaoZhuang() {
        return taoZhuang;
    }

    public void setTaoZhuang(String taoZhuang) {
        this.taoZhuang = taoZhuang;
    }

    public List<String> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<String> authorList) {
        this.authorList = authorList;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

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
