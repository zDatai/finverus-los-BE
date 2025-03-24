package com.zdatai.finverus.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements GrantedAuthority {
    /**
     * Code format : 00 - Module, 000 - Menu, 000 - Event
     */
    private String permissionCode;

    @Override
    public String getAuthority() {
        return permissionCode;
    }
}
