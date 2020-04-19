package com.whu.bookstore_server.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.whu.bookstore_server.domain.Ad;
import com.whu.bookstore_server.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user/menu")
@Component("MenuController")
public class MenuController {
    @Autowired
    private AdService adService;

    @PostMapping("/all")
    public HashMap<String, Object> all(@RequestBody String body) {
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        List<Ad> menus = adService.getMenus();

        ret.put("menus", menus);
        ret.put("state", "ok");
        return ret;
    }
}
