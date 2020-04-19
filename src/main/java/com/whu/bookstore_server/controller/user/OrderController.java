package com.whu.bookstore_server.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.whu.bookstore_server.domain.MyOrder;
import com.whu.bookstore_server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/order")
@Component("OrderController")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/all")
    public HashMap<String, Object> all(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String userId = object.getString("userId");
        List<MyOrder> myOrders = orderService.getOrdersByUserId(userId);
        ret.put("orders", myOrders);
        ret.put("state", "ok");
        return ret;
    }

    @PostMapping("/add")
    public HashMap<String, Object> add(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        MyOrder myOrder = new MyOrder();
        myOrder.setBookId(object.getString("bookId"));
        myOrder.setUserId(object.getString("userId"));
        myOrder.setPayedMoney(object.getDouble("payedMoney"));
        myOrder.setOrderId(object.getString("orderId"));
        myOrder.setTime(dateFormat.format(new Date()));
        String state;
        if (myOrder.getOrderId() == null || myOrder.getOrderId().equals("")) {
            myOrder.setOrderId(UUID.randomUUID().toString());
            state = orderService.addOrder(myOrder) == 1 ? "ok" : "failAdd";
        } else {
            state = orderService.updateOrder(myOrder) == 1 ? "ok" : "failUpdate";
        }
        ret.put("state", state);
        return ret;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> delete(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String orderId = object.getString("orderId");
        String state = orderService.deleteOrderById(orderId)== 1 ? "ok" : "failDelete";
        ret.put("carts", state);
        ret.put("state", "ok");
        return ret;
    }

}
