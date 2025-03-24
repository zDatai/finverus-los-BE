package com.zdatai.finverus.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "username required")
    private String username;
    @NotBlank(message = "passcode required")
    private String passcode;
}
