package com.zdatai.finverus.request.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePermissionRequest {
    private long roleId;
    private List<PermissionEventRequest>  permissionEvents;
}
