package com.zdatai.finverus.dto.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {
    private String eventCode;
    private String description;
    private long id;
    private String permissionCode;
}
