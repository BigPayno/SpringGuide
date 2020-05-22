package com.spring.guide.security.jwt.security.authorization;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Set;

/**
 * @author payno
 * @date 2020/5/21 11:42
 * @description
 */
@Data
public class SelfUserDetails implements UserDetails, Serializable {
    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }
}
