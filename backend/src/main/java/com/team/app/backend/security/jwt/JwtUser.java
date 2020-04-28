package com.team.app.backend.security.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtUser implements org.springframework.security.core.userdetails.UserDetails {

    private String username;
    private String password;
    private java.util.Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;

    public JwtUser(String username,
                   String password,
                   Collection<? extends GrantedAuthority> authorities,
                   boolean enabled) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
