package com.whu.WGSJ_Server.service.impl;

import com.whu.WGSJ_Server.domain.Admin;
import com.whu.WGSJ_Server.domain.Role;
import com.whu.WGSJ_Server.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    RedisServiceImpl redisService;

    @Override
    public UserDetails authenticateToken(@NonNull String token,String id) {
        Object obj = redisService.getUserOrAdminBySessionId(token);
        if(obj!=null) {
            redisService.updateExpireTime(token);
            if (obj instanceof com.whu.WGSJ_Server.domain.User) {
                com.whu.WGSJ_Server.domain.User user = (com.whu.WGSJ_Server.domain.User) obj;
                if (id.equals(user.getUserId())) {
                    return User.builder()
                            .username(id)
                            .password("")
                            .authorities(Role.USER)
                            .build();
                }
            } else if (obj instanceof Admin) {
                Admin admin = (Admin) obj;
                if (id.equals(admin.getAdminId())) {
                    return User.builder()
                            .username(id)
                            .password("")
                            .authorities(Role.ADMIN,Role.USER)
                            .build();
                }
            }
        }

        return User.builder()
                .username(id)
                .password("")
                .authorities(Role.VISITOR).build();
    }
}
