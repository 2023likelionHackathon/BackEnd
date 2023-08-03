package com.project.market.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserPrincipal extends User {
    private final Long id;
    public UserPrincipal(com.project.market.domain.User user) {
        super(user.getUserId(), user.getPw(), List.of(new SimpleGrantedAuthority(user.getRoleKey())));
        this.id = user.getId();
    }

    public Long getId(){
        return id;
    }
}
