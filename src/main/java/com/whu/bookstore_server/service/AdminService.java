package com.whu.bookstore_server.service;

import com.whu.bookstore_server.domain.Admin;

public interface AdminService {

    Admin getAdminById(String adminId);

    Integer addAdmin(Admin admin);

    Integer updateAdmin(Admin admin);

    Integer deleteAdmin(String adminId);
}
