package com.zdatai.finverus.request.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionEventRequest {
    private long permissionId;
    private List<Long> eventIds;
}
