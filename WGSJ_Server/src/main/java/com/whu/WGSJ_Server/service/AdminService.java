package com.whu.WGSJ_Server.service;

import com.whu.WGSJ_Server.domain.Admin;

public interface AdminService {

    Admin getAdminById(String adminId);

    Integer addAdmin(Admin admin);

    Integer updateAdmin(Admin admin);

    Integer deleteAdmin(String adminId);
}
