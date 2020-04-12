package com.whu.WGSJ_Server.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.whu.WGSJ_Server.domain.Banner;
import com.whu.WGSJ_Server.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user/banner")
@Component("BannerController")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    @PostMapping("banners")
    public HashMap<String, Object> banners(@RequestBody String body){
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String date = object.getString("date");
        List<Banner> banners = bannerService.getBannerByDate(date);

        ret.put("state", "ok");
        ret.put("banners", banners);
        return ret;
    }
}


