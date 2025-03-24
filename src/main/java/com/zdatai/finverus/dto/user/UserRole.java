package com.zdatai.finverus.dto.user;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class UserRole implements GrantedAuthority {
    private Role role;

    @Override
    public String getAuthority() {
        return "";
    }
}
