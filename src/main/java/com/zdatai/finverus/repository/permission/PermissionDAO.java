package com.zdatai.finverus.repository.permission;

import com.zdatai.finverus.dto.permission.PermissionEventDTO;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.user.*;
import com.zdatai.finverus.request.permission.RolePermissionRequest;

import java.util.List;
import java.util.Set;

public interface PermissionDAO {
    void createUserAccount(UserAccount userAccount)throws FinVerusException;
    void createPermission(Permission permission, Set<PermissionEvent> permissionEvents)throws FinVerusException;
    List<Event> getEventListByIdList(List<Long> eventIdList)throws FinVerusException;
    void createEvent(Event event)throws FinVerusException;
    List<PermissionEvent> getPermissionEventsByIdList(List<Long> idList)throws FinVerusException;
    Role getRoleById(long id)throws FinVerusException;
    List<Role> getRolesByIdList(List<Long> idList)throws FinVerusException;
    UserAccount getUserAccountById(long id)throws FinVerusException;
    void createRolePermission(RolePermissionRequest rolePermissionRequest)throws FinVerusException;
    void createUserRoleMap(List<UserRole> userRoles)throws FinVerusException;
    List<PermissionEventDTO> getAllPermissionsByProduct(int productId)throws FinVerusException;
    List<PermissionEventDTO> getAllPermissionsByRole(List<Long> roleIdList, int productId)throws FinVerusException;
    List<Permission> getPermissionListByIdList(List<Long> idList)throws FinVerusException;
    Permission getPermissionById(long id)throws FinVerusException;
    List<Long> getRoleIdsByUser(long userId)throws FinVerusException;

    void createMenu(Menu menu, Set<MenuEvent> menuEvents)throws FinVerusException;
    void createMenuPermission(RolePermissionRequest rolePermissionRequest)throws FinVerusException;
    List<PermissionEventDTO> getMenuPermissionsByProduct(int productId)throws FinVerusException;
    void createRoleMenuPermission(RolePermissionRequest rolePermissionRequest)throws FinVerusException;
    Menu getMenuById(long id)throws FinVerusException;
    MenuEvent getMenuEventById(long id)throws FinVerusException;
    List<MenuEvent> getMenuEventListByIds(List<Long> idList)throws FinVerusException;
}
