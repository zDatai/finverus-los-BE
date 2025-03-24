package com.zdatai.finverus.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Setter
@Getter
public class FinVerusUserDetail implements UserDetails {
    private Set<Permission> authorities;
    private long userId;
    private String userName;
    private String passcode;
    private boolean accountNonExpired;
    private boolean credentialNonExpired;
    private boolean accountNonLocked;
    private boolean active;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passcode;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public Set<Permission> getPermissionSet(){
        return authorities;
    }
}
