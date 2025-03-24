package com.zdatai.finverus.dto.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDTO{
    private long id;
    private String description;
    private List<EventDTO> events;
    private List<PermissionDTO> subPermissions;
    private String permissionCode;
}
