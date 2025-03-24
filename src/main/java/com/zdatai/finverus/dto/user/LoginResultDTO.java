package com.zdatai.finverus.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResultDTO {
    private String token;
    private long expiredIn;
}
