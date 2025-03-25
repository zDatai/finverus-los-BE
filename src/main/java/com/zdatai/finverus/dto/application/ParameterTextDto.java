package com.zdatai.finverus.dto.application;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParameterTextDto {
    private String param;
    private String value;
}
