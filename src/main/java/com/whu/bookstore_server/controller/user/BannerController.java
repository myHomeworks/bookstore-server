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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user/banner")
@Component("BannerController")
public class BannerController {
    @Autowired
    private AdService adService;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping("today")
    public HashMap<String, Object> someday(@RequestBody String body){
        HashMap<String, Object> ret = new HashMap<>();
//        JSONObject object = JSONObject.parseObject(body);
//        String userId = object.getString("userId");
        String date = dateFormat.format(new Date());
        List<Ad> banners = adService.getBannerByDate(date);

        ret.put("state", "ok");
        ret.put("banners", banners);
        return ret;
    }
}


