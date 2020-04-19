package com.whu.bookstore_server.domain;

import com.baomidou.mybatisplus.annotations.TableId;

public class MyOrder {
    @TableId
    private String orderId;

    private String addressId = "ouuta5CoU25vhFbCX_Zr2nNma4Js";

    private String bookId;

    private String userId;

    private Double payedMoney;

    private String payType = "线上支付";

    private String state = "待发货";

    private Integer isDeleted = 0;

    private String remark = "";

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getPayedMoney() {
        return payedMoney;
    }

    public void setPayedMoney(Double payedMoney) {
        this.payedMoney = payedMoney;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
