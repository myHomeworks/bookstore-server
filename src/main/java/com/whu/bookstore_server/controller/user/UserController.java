package com.whu.bookstore_server.controller.user;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whu.bookstore_server.config.APIConfig;
import com.whu.bookstore_server.domain.Admin;
import com.whu.bookstore_server.domain.User;
import com.whu.bookstore_server.service.AdminService;
import com.whu.bookstore_server.service.ConfigService;
import com.whu.bookstore_server.service.RedisService;
import com.whu.bookstore_server.service.UserService;
import net.sf.jsqlparser.expression.operators.relational.JsonOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/user/user")
@Component("UserController")
public class UserController {
    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private AdminService adminService;

    private static String appId;
    private static String secret;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/login")
    public HashMap<String, Object> login(@RequestBody String body) throws IOException {
        if (UserController.appId == null) {
            UserController.appId = configService.getConfigByKey("appId").getValue();
            UserController.secret = configService.getConfigByKey("secret").getValue();
            log.info("appId:\t\t" + UserController.appId);
        }

        HashMap<String, Object> ret = new HashMap<>(); // 返回值
        JSONObject object = JSONObject.parseObject(body);

        String openId = this.getOpenIdFromCode(object.getString("code"));
        if(openId == null){
            ret.put("state", "failGetOpenId");
            return ret;
        }
        User user = userService.getUserById(openId);

        if (user == null) { // 创建新用户
            user = this.buildUserNew(object, openId);
            if (userService.addUser(user) != 1) {
                ret.put("state", "failAdd");
                return ret;
            }
        } else { // 更新老用户
            redisService.delSessionId(user.getSessionId());
            this.updateUser(user, object);
            if (userService.updateUser(user) != 1) {
                ret.put("state", "failUpdate");
                return ret;
            }
            // 判断用户是否为管理员
            if (adminService.getAdminById(openId) != null) {
                ret.put("isAdmin", true);
            }
        }

        redisService.saveObjectInstanceBySessionId(user.getSessionId(), user);

        ret.put("state", "ok");
        ret.put("userId", user.getUserId());
        ret.put("sessionId", user.getSessionId());
        return ret;
    }

    // 发送微信request请求,通过code获得openId
    private String getOpenIdFromCode(String code) throws IOException {
        try{
            RestTemplate restTemplate = new RestTemplate();
            String params = "?appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
            String url = APIConfig.openIdURL + params;
            String response = restTemplate.getForObject(url, String.class);

            JsonNode node = this.mapper.readTree(response);
            return node.get("openid").asText();
        }catch (Exception ex){
            log.error("Something Wrong When Get OpenId:code"+code+"\n"+ex.getMessage());
            return null;
        }
    }

    // 新注册时创建新用户
    private User buildUserNew(JSONObject object, String openid) {
        User user = new User();

        user.setUserId(openid);
        user.setTime(dateFormat.format(new Date()));

        this.updateUser(user, object);
        user.setNickname(user.getName());
        return user;
    }

    // 更新老用户的部分信息
    private void updateUser(User user, JSONObject object) {
        JSONObject userInfo = (JSONObject) object.get("userInfo");
        if(userInfo!=null){
            user.setName(userInfo.getString("nickName"));
            user.setGender(userInfo.getInteger("gender"));
            user.setAvatar(userInfo.getString("avatarUrl"));
        }

        JSONObject location = (JSONObject) object.get("location");
        if(location!=null){
            double latitude = location.getBigDecimal("latitude").doubleValue();
            double longitude = location.getBigDecimal("longitude").doubleValue();
            user.setLatitude(latitude);
            user.setLongitude(longitude);
        }

        String userSessionId = UUID.randomUUID().toString();
        user.setSessionId(userSessionId);
    }
}
