package com.whu.WGSJ_Server.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whu.WGSJ_Server.config.APIConfig;
import com.whu.WGSJ_Server.domain.User;
import com.whu.WGSJ_Server.service.RedisService;
import com.whu.WGSJ_Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Component("UserController")
public class UserController {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

//    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//    @PostMapping("/login")
//    public HashMap<String, String> login(@RequestBody String body) throws IOException {
//        JSONObject object = JSONObject.parseObject(body);
//        HashMap<String, String> ret = new HashMap<>(); // 返回值
//        String code = (String) object.get("code");
//        JSONObject userInfo = (JSONObject) object.get("userInfo");
//        Double latitude = object.getBigDecimal("latitude").doubleValue();
//        Double longitude = object.getBigDecimal("longitude").doubleValue();
//        RestTemplate restTemplate = new RestTemplate();// 发送request请求
//        String params = "?appid=" + APIConfig.wxspAppid + "&secret=" + APIConfig.wxspSecret + "&js_code=" + code + "&grant_type=authorization_code";//参数
//        String url = APIConfig.openIdURL + params;
//        String response = restTemplate.getForObject(url, String.class);
//
//        JsonNode node = this.mapper.readTree(response);
//        String openid = node.get("openid").asText();
////        String sessionKey = node.get("session_key").asText(); // 微信的session_key
//
//        User user = new User();
//        user.setUserId(openid);
//        user.setLatitude(latitude != null ? latitude : 0.0);
//        user.setLongitude(longitude != null ? longitude : 0.0);
//
//        parseJson2User(userInfo, user);
//        String userSessionKey = UUID.randomUUID().toString(); // 我们的sessionKey
//        user.setSessionId(userSessionKey);
//        User check = userService.getUserById(openid);
//        if (check == null) {
//            user.setTime(dateFormat.format(new Date()));
//            if (userService.register(user) == 1)
//                check = user;
//            else {
//                ret.put("state", "register fail!");
//                return ret;
//            }
//        } else {
//            redisService.delSessionId(check.getSessionId());
//            updateFromWeixin(check, user);
//            if (userService.updateUser(check) == 1)
//                check = user;
//            else {
//                ret.put("state", "login fail!");
//                return ret;
//            }
//        }
//        redisService.saveUserOrAdminBySessionId(userSessionKey, check);
//
//        ret.put("state", "ok");
//        ret.put("openId", openid);
//        ret.put("sessionKey", userSessionKey);
//        return ret;
//    }
//
//    private void parseJson2User(JSONObject userinfo, User user) {
//        user.setName((String) userinfo.get("nickName"));
//        user.setNickname((String) userinfo.get("nickName"));
//        user.setGender((Integer) userinfo.get("gender"));
//        user.setAvatar((String) userinfo.get("avatarUrl"));
//    }
//
//
//    private void updateFromWeixin(User check, User user) {
//        check.setName(user.getName());
//        check.setGender(user.getGender());
//        check.setAvatar(user.getAvatar());
//        check.setLongitude(user.getLongitude());
//        check.setLatitude(user.getLatitude());
//        check.setSessionId(user.getSessionId());
//    }

}
