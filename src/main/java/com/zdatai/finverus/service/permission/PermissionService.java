package com.zdatai.finverus.service.permission;

import com.zdatai.finverus.dto.permission.PermissionDTO;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.request.permission.*;

import java.util.List;

public interface PermissionService {
    void createUserAccount(UserAccountRequest userAccountRequest)throws FinVerusException;
    void createPermission(PermissionRequest permissionRequest)throws FinVerusException;
    void createEvent(EventRequest eventRequest)throws FinVerusException;
    void createRolePermission(RolePermissionRequest rolePermissionRequest)throws FinVerusException;
    void createUserRoleMap(UserRolMapRequest userRolMapRequest)throws FinVerusException;
    List<PermissionDTO> getPermissionsByProduct(int productId)throws FinVerusException;
    List<PermissionDTO> getPermissionsByRole(long roleId, int productId)throws FinVerusException;
    List<PermissionDTO> getPermissionsByUser(long userId, int productId)throws FinVerusException;
}
