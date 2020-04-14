package com.whu.WGSJ_Server.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.whu.WGSJ_Server.domain.User;

import java.util.List;

public interface UserService {

    // 普通用户的接口
    Integer addUser(User user);

    // 后台管理的接口
    List<User> getUsersAll(Page<User> page, String startTime, String endTime);

    List<User> getUsersByNickname(String nickname, Page<User> page);

    List<User> getUsersList(Page<User> page);
    // 普通用户与后台管理公共接口
    User getUserById(String userId);

    Integer updateUser(User user);

}
