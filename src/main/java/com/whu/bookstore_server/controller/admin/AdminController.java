package com.whu.bookstore_server.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.whu.bookstore_server.domain.Admin;
import com.whu.bookstore_server.domain.User;
import com.whu.bookstore_server.service.AdminService;
import com.whu.bookstore_server.service.RedisService;
import com.whu.bookstore_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/admin/admin")
@Component("AdminController")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @PostMapping("/loginByWeChat")
    public HashMap<String, Object> loginByWeChat(@RequestBody String body){
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String adminId = object.getString("adminId");
        String weChatSessionId = object.getString("weChatSessionId");
        if(!userService.getUserById(adminId).getSessionId().equals(weChatSessionId)){
            ret.put("state", "failAuthentication");
            return ret;
        }
        Admin admin = adminService.getAdminById(adminId);
        redisService.delSessionId(admin.getSessionId());
        String adminSessionId = UUID.randomUUID().toString();
        admin.setSessionId(adminSessionId);
        if(adminService.updateAdmin(admin) != 1){
            ret.put("state", "failUpdate");
            return ret;
        }
        ret.put("sessionId", adminSessionId);
        ret.put("state", "ok");
        return ret;
    }
}
