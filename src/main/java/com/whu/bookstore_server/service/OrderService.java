package com.whu.bookstore_server.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.whu.bookstore_server.domain.Order;

import java.util.List;

public interface OrderService {
    // 普通用户的

    // 管理员的

    Integer updateOrder(Order order);

    // 公共的
    Integer addOrder(Order order);

    Order getOrderById(String orderId);

    Integer deleteOrderById(String orderId); // 假删除（账单外键）

    // 调用规则 bookName & authorName & publisherName & addressName & consignee & mobile实参null表不使用该条件;
    List<Order> getOrdersByComplexConditions(Page<Order> page, String bookName, String authorName, String publisherName,
                                             String startTime, String endTime, Double startPrice, Double endPrice,
                                             String addressName, String consignee, String mobile);

}
