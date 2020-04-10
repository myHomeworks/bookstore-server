package com.whu.WGSJ_Server.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    USER,
    VISITOR;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
