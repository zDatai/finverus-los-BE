package com.zdatai.finverus.service.impl.permission;

import com.zdatai.finverus.dto.permission.EventDTO;
import com.zdatai.finverus.dto.permission.PermissionDTO;
import com.zdatai.finverus.dto.permission.PermissionEventDTO;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.user.*;
import com.zdatai.finverus.repository.permission.PermissionDAO;
import com.zdatai.finverus.request.permission.*;
import com.zdatai.finverus.service.permission.PermissionService;
import com.zdatai.finverus.utility.PasswordEncryptionUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDAO permissionDAO;

    @Autowired
    private PasswordEncryptionUtility passwordEncryptionUtility;

    @Override
    public void createUserAccount(UserAccountRequest userAccountRequest) throws FinVerusException {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserName(userAccountRequest.getUserName());
        userAccount.setPassword(passwordEncryptionUtility.encryptPassword(userAccountRequest.getPassword()));
        userAccount.setActive(userAccountRequest.isActive() ? 1 : 0);
        permissionDAO.createUserAccount(userAccount);
    }

    @Override
    public void createPermission(PermissionRequest permissionRequest) throws FinVerusException {
        List<Event> eventList = null;
        if(permissionRequest.getEventIdList() != null && !permissionRequest.getEventIdList().isEmpty()){
            eventList = permissionDAO.getEventListByIdList(permissionRequest.getEventIdList());
        }
        Set<MenuEvent> list = new HashSet<>();
        Menu menu = new Menu();
        menu.setMenuCode(permissionRequest.getCode());
        menu.setMenuDescription(permissionRequest.getDescription());
        menu.setActive(permissionRequest.getActive());
        menu.setParentId(permissionRequest.getParentId());
        menu.setProductId(permissionRequest.getProductId());

        if(eventList != null){
            eventList.forEach((event -> {
                MenuEvent menuEvent = new MenuEvent();
                menuEvent.setEvent(event);
                menuEvent.setMenu(menu);
                list.add(menuEvent);
            }));
        }
        permissionDAO.createMenu(menu, list);
    }

    @Override
    public void createEvent(EventRequest eventRequest) throws FinVerusException {
        Event event = new Event();
        event.setEventCode(eventRequest.getEventCode());
        event.setDescription(eventRequest.getDescription());
        permissionDAO.createEvent(event);
    }

    @Override
    public void createRolePermission(RolePermissionRequest rolePermissionRequest) throws FinVerusException {
        permissionDAO.createRoleMenuPermission(rolePermissionRequest);
    }

    @Override
    public void createUserRoleMap(UserRolMapRequest userRolMapRequest) throws FinVerusException {
        List<Role> roles = permissionDAO.getRolesByIdList(userRolMapRequest.getRoleIds());
        UserAccount user = permissionDAO.getUserAccountById(userRolMapRequest.getUserId());
        if(user == null){
            throw new FinVerusException("User Account not found");
        }
        if(roles == null || roles.isEmpty()){
            throw new FinVerusException("Roles not found");
        }
        List<UserRole> userRoles = new ArrayList<>();
        roles.forEach((role)->{
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setActive(userRolMapRequest.getActive());
            userRole.setRole(role);
            userRole.setEffectiveFromDate(userRolMapRequest.getFromDate());
            userRole.setEffectiveToDate(userRolMapRequest.getToDate());
            userRoles.add(userRole);
        });
        permissionDAO.createUserRoleMap(userRoles);
    }

    @Override
    public List<PermissionDTO> getPermissionsByProduct(int productId) throws FinVerusException {
        List<PermissionDTO> permissions = new ArrayList<>();
        List<PermissionEventDTO> permissionList = permissionDAO.getMenuPermissionsByProduct(productId);
        Map<Long, List<PermissionEventDTO>> parentGroup = permissionList.stream().collect(Collectors.groupingBy(PermissionEventDTO::getParentId));
        parentGroup.forEach( (parentId , list) -> {
            if(parentId == 0){
                list.forEach( (permission) -> {
                    PermissionDTO dto = new PermissionDTO();
                    dto.setId(permission.getId());
                    dto.setDescription(permission.getDescription());
                    addEvents(permission, dto);
                    permissions.add(dto);
                });
            }else{
                list.forEach( (permission) -> {
                    permissions.forEach((dto)->{
                        checkSubList(permission, dto, false, null);
                    });
                });
            }

        });
        return permissions;
    }

    @Override
    public List<PermissionDTO> getPermissionsByRole(long roleId, int productId) throws FinVerusException {
        List<PermissionDTO> permissions = new ArrayList<>();
        List<PermissionEventDTO> rolePermissions = permissionDAO.getAllPermissionsByRole(List.of(roleId), productId);
        Map<Long, List<PermissionEventDTO>> parentGroup = rolePermissions.stream().collect(Collectors.groupingBy(PermissionEventDTO::getParentId));
        parentGroup.forEach( (parentId , list) -> {
            if(parentId == 0){
                list.forEach( (permission) -> {
                    PermissionDTO dto = new PermissionDTO();
                    dto.setId(permission.getId());
                    dto.setDescription(permission.getDescription());
//                    if(permission.getEventSet().isEmpty())
                    dto.setPermissionCode(permission.getPermissionCode());
                    addEvents(permission, dto);
                    permissions.add(dto);
                });
            }else{
                list.forEach( (permission) -> {
                    permissions.forEach((dto)->{
                        checkSubList(permission, dto, false, null);
                    });
                });
            }

        });
        return permissions;
    }

    @Override
    public List<PermissionDTO> getPermissionsByUser(long userId, int productId) throws FinVerusException {
        List<Long> roleIds = permissionDAO.getRoleIdsByUser(userId);
//        permissionDAO.getAllPermissionsByRole(roleIds, productId);
        List<PermissionDTO> permissions = new ArrayList<>();
        List<PermissionEventDTO> rolePermissions = permissionDAO.getAllPermissionsByRole(roleIds, productId);
        Map<Long, List<PermissionEventDTO>> parentGroup = rolePermissions.stream().collect(Collectors.groupingBy(PermissionEventDTO::getParentId));
        parentGroup.forEach( (parentId , list) -> {
            if(parentId == 0){
                list.forEach( (permission) -> {
                    PermissionDTO dto = new PermissionDTO();
                    dto.setId(permission.getId());
                    dto.setDescription(permission.getDescription());
                    if(permission.getEventSet().isEmpty())
                        dto.setPermissionCode(permission.getPermissionCode());
                    addEvents(permission, dto);
                    permissions.add(dto);
                });
            }else{
                list.forEach( (permission) -> {
                    permissions.forEach((dto)->{
                        checkSubList(permission, dto, false, null);
                    });
                });
            }

        });
        return permissions;
    }

    private boolean checkSubList(PermissionEventDTO permission, PermissionDTO dto, boolean roleAccess, Set<RolePermissionEvent> rolePermissionEvents){
        if(!addPermission(permission, dto, roleAccess, rolePermissionEvents) && dto.getSubPermissions() != null){
            for(PermissionDTO permissionDTO : dto.getSubPermissions()){
                if(addPermission(permission, permissionDTO, roleAccess, rolePermissionEvents)){
                    return true;
                }else{
                    return checkSubList(permission, permissionDTO, roleAccess, rolePermissionEvents);
                }
            }
        }
        return false;
    }

    private boolean addPermission(PermissionEventDTO permission, PermissionDTO dto, boolean roleAccess, Set<RolePermissionEvent> rolePermissionEvents){
        if(permission.getParentId() == dto.getId()){
            PermissionDTO subDTO = new PermissionDTO();
            subDTO.setId(permission.getId());
            subDTO.setDescription(permission.getDescription());
//            if(permission.getEventSet().isEmpty())
            subDTO.setPermissionCode(permission.getPermissionCode());
            addEvents(permission, subDTO);
            if(dto.getSubPermissions() == null){
                List<PermissionDTO> subList = new ArrayList<>();
                subList.add(subDTO);
                dto.setSubPermissions(subList);
            }else{
                dto.getSubPermissions().add(subDTO);
            }
            return true;
        }
        return false;
    }

    private void addEvents(PermissionEventDTO permission, PermissionDTO dto){
        Set<EventDTO> events = permission.getEventSet();
        events.forEach((event)->{
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(event.getId());
            eventDTO.setDescription(event.getDescription());
            eventDTO.setEventCode(event.getEventCode());
            eventDTO.setPermissionCode(event.getPermissionCode());
            if(dto.getEvents() != null){
                dto.getEvents().add(eventDTO);
            }else{
                List<EventDTO> eventDTOList = new ArrayList<>();
                eventDTOList.add(eventDTO);
                dto.setEvents(eventDTOList);
            }
        });
    }

    private void addPermittedEvents(Set<RolePermissionEvent> rolePermissionEventsEvents,PermissionEventDTO permission, PermissionDTO dto){
        List<RolePermissionEvent> permissionEvents = rolePermissionEventsEvents.stream().filter(p->p.getPermission().getId() == permission.getId()).toList();
        List<Event> events = permissionEvents.stream().map(RolePermissionEvent::getEvent).toList();
        events.forEach((event)->{
            EventDTO eventDTO = new EventDTO();
            eventDTO.setId(event.getEventId());
            eventDTO.setDescription(event.getDescription());
            eventDTO.setEventCode(event.getEventCode());
            if(dto.getEvents() != null){
                dto.getEvents().add(eventDTO);
            }else{
                List<EventDTO> eventDTOList = new ArrayList<>();
                eventDTOList.add(eventDTO);
                dto.setEvents(eventDTOList);
            }
        });
    }
}
