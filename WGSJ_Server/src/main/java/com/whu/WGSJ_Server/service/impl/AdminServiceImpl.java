package com.whu.WGSJ_Server.service.impl;

import com.whu.WGSJ_Server.domain.Admin;
import com.whu.WGSJ_Server.mapper.AdminMapper;
import com.whu.WGSJ_Server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin getAdminById(String adminId) {
        return adminMapper.selectById(adminId);
    }

    @Override
    public Integer addAdmin(Admin admin) {
        return adminMapper.insert(admin);
    }

    @Override
    public Integer updateAdmin(Admin admin) {
        return adminMapper.updateById(admin);
    }

    @Override
    public Integer deleteAdmin(String adminId) {
        return adminMapper.deleteById(adminId);
    }
}
