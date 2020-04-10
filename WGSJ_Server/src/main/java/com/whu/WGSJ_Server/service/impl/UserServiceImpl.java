package com.whu.WGSJ_Server.service.impl;

import com.whu.WGSJ_Server.domain.User;
import com.whu.WGSJ_Server.mapper.UserMapper;
import com.whu.WGSJ_Server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public int register(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }
}
