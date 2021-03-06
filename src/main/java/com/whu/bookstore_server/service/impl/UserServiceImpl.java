package com.whu.bookstore_server.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.whu.bookstore_server.domain.User;
import com.whu.bookstore_server.mapper.UserMapper;
import com.whu.bookstore_server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(String userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> getUsersAll(Page<User> page, String startTime, String endTime) {
        return userMapper.selectPage(page, new EntityWrapper<User>()
                .ge("`time`", startTime)
                .le("`time`", endTime)
                .orderBy("`time`", false));
    }

    @Override
    public List<User> getUsersByNickname(String nickname, Page<User> page) {
        return userMapper.selectPage(page, new EntityWrapper<User>()
                .like("`name`", nickname)
                .orderBy("`time`", false));
    }

    @Override
    public List<User> getUsersList(Page<User> page) {
        return userMapper.selectPage(page, new EntityWrapper<User>()
                .orderBy("`time`", false));
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateById(user);
    }
}
