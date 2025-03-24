package com.zdatai.finverus.request.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountRequest {
    private String userName;
    private String password;
    private boolean active;
    private boolean expired;
    private boolean locked;
}
