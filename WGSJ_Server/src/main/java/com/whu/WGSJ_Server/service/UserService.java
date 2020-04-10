package com.whu.WGSJ_Server.service;

import com.whu.WGSJ_Server.domain.User;

public interface UserService {

    // 普通用户的接口
    int register(User user);

    // 后台管理的接口

    // 普通用户与后台管理公共接口
    User getUserById(String userId);

    int updateUser(User user);
}
