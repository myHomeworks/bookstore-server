package com.whu.bookstore_server.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.whu.bookstore_server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/admin/order")
@Component("AdminOrderController")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/moneyGraph")
    public HashMap<String, Object> moneyGraph(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
//        Integer year = object.getInteger("year");
        Integer year = 2020;

        String state = orderService.getOrderMoneyGraph(ret, year) ? "ok" : "failGetData";
        ret.put("state", state);
        return ret;
    }

}
