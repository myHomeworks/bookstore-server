package com.whu.bookstore_server.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.whu.bookstore_server.domain.User;
import com.whu.bookstore_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/user")
@Component("AdminUserController")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/searchUser")
    public HashMap<String, Object> searchUser(@RequestBody String body){
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        String key = object.getString("key");
        Integer pageRequest = object.getInteger("pageRequest");
        Integer pageSize = object.getInteger("pageSize");

        Page<User> page = new Page<User>(pageRequest, pageSize);
        List<User> users = userService.getUsersByNickname(key, page);

        ret.put("resultList", users);
        ret.put("state", "ok");
        return ret;
    }

    @PostMapping("/userList")
    public HashMap<String, Object> userList(@RequestBody String body){
        HashMap<String, Object> ret = new HashMap<>();
        JSONObject object = JSONObject.parseObject(body);
        Integer pageRequest = object.getInteger("pageRequest");
        Integer pageSize = object.getInteger("pageSize");

        Page<User> page = new Page<User>(pageRequest, pageSize);
        List<User> users = userService.getUsersList(page);

        ret.put("resultList", users);
        ret.put("pageNum", (int)Math.ceil(page.getTotal()/ (double)pageSize));
        ret.put("totalNum", page.getTotal());
        ret.put("state", "ok");
        return ret;
    }
}
