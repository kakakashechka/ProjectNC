package com.team.app.backend.security.jwt;

import com.team.app.backend.persistance.model.Role;
import com.team.app.backend.persistance.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

public final class JwtUserFactory {
    public JwtUserFactory() {}

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthority(user.getRole()),
                true
        );
    }

    private static java.util.List<GrantedAuthority> mapToGrantedAuthority(Role role) {
        return Arrays.asList(new SimpleGrantedAuthority(role.getName()));
    }
}
