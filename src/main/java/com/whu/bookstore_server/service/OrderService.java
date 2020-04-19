package com.whu.bookstore_server.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.whu.bookstore_server.domain.MyOrder;

import java.util.HashMap;
import java.util.List;

public interface OrderService {
    // 普通用户的
    List<MyOrder> getOrdersByUserId(String userId);

    // 管理员的

    Integer updateOrder(MyOrder myOrder);

    Boolean getOrderMoneyGraph(HashMap<String, Object> ret, Integer year);

    // 公共的
    Integer addOrder(MyOrder myOrder);

    MyOrder getOrderById(String orderId);

    Integer deleteOrderById(String orderId); // 假删除（账单外键）

    // 调用规则 bookName & authorName & publisherName & addressName & consignee & mobile实参null表不使用该条件;
    List<MyOrder> getOrdersByComplexConditions(Page<MyOrder> page, String bookName, String authorName, String publisherName,
                                               String startTime, String endTime, Double startPrice, Double endPrice,
                                               String addressName, String consignee, String mobile);

}
