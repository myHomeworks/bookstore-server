package com.whu.bookstore_server.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.whu.bookstore_server.domain.ShoppingCart;
import com.whu.bookstore_server.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/user/cart")
@Component("ShoppingCartController")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/all")
    public HashMap<String, Object> all(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String userId = object.getString("userId");
        List<ShoppingCart> shoppingCarts = shoppingCartService.getCartsByUserId(userId);
        ret.put("carts", shoppingCarts);
        ret.put("state", "ok");
        return ret;
    }

    @PostMapping("/add")
    public HashMap<String, Object> add(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        ShoppingCart shoppingCart = JSONObject.parseObject(body, new TypeReference<ShoppingCart>() {
        });
        String state;
        if (shoppingCart.getShoppingCartId() == null || shoppingCart.getShoppingCartId().equals("")) {
            shoppingCart.setShoppingCartId(UUID.randomUUID().toString());
            state = shoppingCartService.addCart(shoppingCart) == 1 ? "ok" : "failAdd";
        } else {
            state = shoppingCartService.updateCart(shoppingCart) == 1 ? "ok" : "failUpdate";
        }
        ret.put("shoppingCartId", shoppingCart.getShoppingCartId());
        ret.put("state", state);
        return ret;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> delete(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String shoppingCartId = object.getString("shoppingCartId");
        String state = shoppingCartService.delCartById(shoppingCartId) == 1 ? "ok" : "failDelete";
        ret.put("carts", state);
        ret.put("state", "ok");
        return ret;
    }
}
