package com.zdatai.finverus.repository.impl.permission;

import com.zdatai.finverus.dto.permission.EventDTO;
import com.zdatai.finverus.dto.permission.PermissionEventDTO;
import com.zdatai.finverus.enums.ProductEnum;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.user.*;
import com.zdatai.finverus.repository.permission.PermissionDAO;
import com.zdatai.finverus.request.permission.RolePermissionRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class PermissionDAOImpl implements PermissionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUserAccount(UserAccount userAccount) throws FinVerusException {
        entityManager.persist(userAccount);
    }

    @Override
    public void createPermission(Permission permission, Set<PermissionEvent> permissionEvents) throws FinVerusException {
        entityManager.persist(permission);
        permissionEvents.forEach((pe)->{
            entityManager.persist(pe);
        });
    }

    @Override
    public List<Event> getEventListByIdList(List<Long> eventIdList) throws FinVerusException {
        final String eventQuery = "SELECT e FROM Event e WHERE e.eventId IN( :Ids)";
        return entityManager.createQuery(eventQuery, Event.class)
                .setParameter("Ids", eventIdList)
                .getResultList();
    }

    @Override
    public void createEvent(Event event) throws FinVerusException {
        entityManager.persist(event);
    }

    @Override
    public List<PermissionEvent> getPermissionEventsByIdList(List<Long> idList) throws FinVerusException {
        final String query = "SELECT pe FROM PermissionEvent pe LEFT JOIN FETCH pe.permission pep " +
                "LEFT JOIN FETCH pe.event pee WHERE pe.permissionEventId IN( :ids)";
        return entityManager.createQuery(query, PermissionEvent.class)
                .setParameter("ids", idList)
                .getResultList();
    }

    @Override
    public Role getRoleById(long id) throws FinVerusException {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> getRolesByIdList(List<Long> idList) throws FinVerusException {
        final String query = "SELECT r FROM Role r WHERE r.roleId IN( :Ids)";
        return entityManager.createQuery(query, Role.class)
                .setParameter("Ids", idList)
                .getResultList();
    }

    @Override
    public UserAccount getUserAccountById(long id) throws FinVerusException {
        return entityManager.find(UserAccount.class, id);
    }

    @Override
    public void createRolePermission(RolePermissionRequest rolePermissionRequest) throws FinVerusException {
       Role role = getRoleById(rolePermissionRequest.getRoleId());
        rolePermissionRequest.getPermissionEvents().forEach((permissionEvents)->{
            List<Event> events = getEventListByIdList(permissionEvents.getEventIds());
            Permission permission = getPermissionById(permissionEvents.getPermissionId());
            if(events == null || events.isEmpty()){
                RolePermissionEvent rolePermissionEvent = new RolePermissionEvent();
                rolePermissionEvent.setRole(role);
                rolePermissionEvent.setPermission(permission);
                rolePermissionEvent.setEvent(null);
                rolePermissionEvent.setActive(1);
                rolePermissionEvent.setPermissionCode(generateCode(rolePermissionRequest.getRoleId(),
                        permission.getProductId(),
                        permission.getId(),
                        permission.getParentId(),
                        null));
                entityManager.persist(rolePermissionEvent);
            }else{
                events.forEach((event)->{
                    RolePermissionEvent rolePermissionEvent = new RolePermissionEvent();
                    rolePermissionEvent.setRole(role);
                    rolePermissionEvent.setPermission(permission);
                    rolePermissionEvent.setEvent(event);
                    rolePermissionEvent.setActive(1);
                    rolePermissionEvent.setPermissionCode(generateCode(rolePermissionRequest.getRoleId(),
                            permission.getProductId(),
                            permission.getId(),
                            permission.getParentId(),
                            event.getEventCode()));
                    entityManager.persist(rolePermissionEvent);
                });
            }
        });
    }

    @Override
    public void createUserRoleMap(List<UserRole> userRoles) throws FinVerusException {
        userRoles.forEach((account)->{
            entityManager.persist(account);
        });
    }

    @Override
    public List<PermissionEventDTO> getAllPermissionsByProduct(int productId) throws FinVerusException {
        Map<Long, PermissionEventDTO> permissionEventMap = new HashMap<>();
        final String query = "SELECT p, pe FROM Permission p LEFT JOIN p.permissionEvents pe WHERE p.productId = :product AND p.active=1 ORDER BY p.id ASC";
        List<Object> list = entityManager.createQuery(query, Object.class)
                .setParameter("product", productId)
                .getResultList();
        if(list != null){
            list.forEach((row)->{
                Object[] cols = (Object[]) row;
                Permission permission = (Permission)cols[0];
                PermissionEvent permissionEvent = cols[1] != null ? (PermissionEvent)cols[1]: null;
                PermissionEventDTO permissionEventDTO = null;
                if(permissionEventMap.get(permission.getId()) != null){
                    permissionEventDTO = permissionEventMap.get(permission.getId());
                }else{
                    permissionEventDTO = new PermissionEventDTO();
                    permissionEventDTO.setProductId(permission.getProductId());
                    permissionEventDTO.setId(permission.getId());
                    permissionEventDTO.setParentId(permission.getParentId());
                    permissionEventDTO.setDescription(permission.getDescription());
                    permissionEventDTO.setActive(permission.getActive());
                }
                if(permissionEvent != null){
                    EventDTO eventDTO = new EventDTO();
                    eventDTO.setEventCode(permissionEvent.getEvent().getEventCode());
                    eventDTO.setDescription(permissionEvent.getEvent().getDescription());
                    eventDTO.setId(permissionEvent.getEvent().getEventId());
                    permissionEventDTO.getEventSet().add(eventDTO);
                }
                permissionEventMap.put(permission.getId(), permissionEventDTO);
            });
        }
        return permissionEventMap.values().stream().toList();
    }

    @Override
    public List<PermissionEventDTO> getAllPermissionsByRole(List<Long> roleIdList, int productId) throws FinVerusException {
        Map<Long, PermissionEventDTO> permissionEventMap = new HashMap<>();
        final String query = "SELECT rm, rme FROM RoleMenu rm JOIN FETCH rm.role r JOIN FETCH rm.menu m LEFT JOIN FETCH rm.roleMenuEventSet rme " +
                "LEFT JOIN FETCH rme.menuEvent rmem LEFT JOIN FETCH rmem.event rmeme WHERE r.roleId IN :roleIds" +
                " AND m.productId = :productId" ;
        List<Object> list = entityManager.createQuery(query, Object.class)
                .setParameter("roleIds", roleIdList)
                .setParameter("productId", productId)
                .getResultList();
        if(list != null){
            list.forEach((row)->{
                Object[] cols = (Object[]) row;
                RoleMenu roleMenu = cols[0] != null ? (RoleMenu) cols[0] : null;
                RoleMenuEvent roleMenuEvent = cols[1] != null ? (RoleMenuEvent) cols[1] : null;
                PermissionEventDTO permissionEventDTO = null;
                if(roleMenu != null){
                    if(permissionEventMap.get(roleMenu.getMenu().getMenuId()) != null){
                        permissionEventDTO = permissionEventMap.get(roleMenu.getMenu().getMenuId());
                    }else{
                        permissionEventDTO = new PermissionEventDTO();
                        permissionEventDTO.setProductId(roleMenu.getMenu().getProductId());
                        permissionEventDTO.setId(roleMenu.getMenu().getMenuId());
                        permissionEventDTO.setParentId(roleMenu.getMenu().getParentId());
                        permissionEventDTO.setDescription(roleMenu.getMenu().getMenuDescription());
                        permissionEventDTO.setActive(roleMenu.getMenu().getActive());
                        permissionEventDTO.setPermissionCode(roleMenu.getPermissionCode());
                    }
                    if(roleMenuEvent != null){
                        EventDTO eventDTO = new EventDTO();
                        eventDTO.setEventCode(roleMenuEvent.getMenuEvent().getEvent().getEventCode());
                        eventDTO.setDescription(roleMenuEvent.getMenuEvent().getEvent().getDescription());
                        eventDTO.setId(roleMenuEvent.getMenuEvent().getEvent().getEventId());
                        eventDTO.setPermissionCode(roleMenuEvent.getPermissionCode());
                        if(!permissionEventDTO.hasEvent(eventDTO.getId()))
                            permissionEventDTO.getEventSet().add(eventDTO);
                    }else{
//                        permissionEventDTO.setPermissionCode(rolePermissionEvent.getPermissionCode());
                        log.info("** Events not found for Menu {} **",permissionEventDTO.getId());
                    }
                    permissionEventMap.put(roleMenu.getMenu().getMenuId(), permissionEventDTO);
                }
            });
        }
        return permissionEventMap.values().stream().toList();
    }

    @Override
    public List<Permission> getPermissionListByIdList(List<Long> idList) throws FinVerusException {
        final String query = "SELECT p FROM Permission p LEFT JOIN p.permissionEvents pe WHERE p.id IN( :ids)";
        return entityManager.createQuery(query, Permission.class)
                .setParameter("ids", idList)
                .getResultList();
    }

    @Override
    public Permission getPermissionById(long id) throws FinVerusException {
        return entityManager.find(Permission.class, id);
    }

    @Override
    public List<Long> getRoleIdsByUser(long userId) throws FinVerusException {
        Set<Long> idSet = new HashSet<>();
        final String query = "SELECT ur, urr.roleId FROM UserRole ur JOIN FETCH ur.role urr JOIN FETCH ur.user uru WHERE uru.userId= :userId " +
                "AND ur.effectiveFromDate <= :toDay AND ur.effectiveToDate >= :toDay AND ur.active=1";
        List<Object> list = entityManager.createQuery(query, Object.class)
                .setParameter("userId", userId)
                .setParameter("toDay", new Date())
                .getResultList();
        if(list != null){
            list.forEach((row)->{
                Object[] cols = (Object[]) row;
                Long id = cols[1] != null ? (Long) cols[1] : 0;
                idSet.add(id);
            });
        }
        return idSet.stream().toList();
    }

    @Override
    public void createMenu(Menu menu, Set<MenuEvent> menuEvents) throws FinVerusException {
        entityManager.persist(menu);
        menuEvents.forEach((me) -> {
            entityManager.persist(me);
        });
    }

    @Override
    public void createMenuPermission(RolePermissionRequest rolePermissionRequest) throws FinVerusException {

    }

    @Override
    public List<PermissionEventDTO> getMenuPermissionsByProduct(int productId) throws FinVerusException {
        Map<Long, PermissionEventDTO> permissionEventMap = new HashMap<>();
        final String query = "SELECT menu, me FROM Menu menu LEFT JOIN menu.menuEventSet me WHERE menu.productId = :product AND menu.active=1 ORDER BY menu.id ASC";
        List<Object> list = entityManager.createQuery(query, Object.class)
                .setParameter("product", productId)
                .getResultList();
        if(list != null){
            list.forEach((row)->{
                Object[] cols = (Object[]) row;
                Menu menu = (Menu) cols[0];
                MenuEvent menuEvent = cols[1] != null ? (MenuEvent) cols[1]: null;
                PermissionEventDTO permissionEventDTO = null;
                if(permissionEventMap.get(menu.getMenuId()) != null){
                    permissionEventDTO = permissionEventMap.get(menu.getMenuId());
                }else{
                    permissionEventDTO = new PermissionEventDTO();
                    permissionEventDTO.setProductId(menu.getProductId());
                    permissionEventDTO.setId(menu.getMenuId());
                    permissionEventDTO.setParentId(menu.getParentId());
                    permissionEventDTO.setDescription(menu.getMenuDescription());
                    permissionEventDTO.setActive(menu.getActive());
                }
                if(menuEvent != null){
                    EventDTO eventDTO = new EventDTO();
                    eventDTO.setEventCode(menuEvent.getEvent().getEventCode());
                    eventDTO.setDescription(menuEvent.getEvent().getDescription());
                    eventDTO.setId(menuEvent.getEvent().getEventId());
                    permissionEventDTO.getEventSet().add(eventDTO);
                }
                permissionEventMap.put(menu.getMenuId(), permissionEventDTO);
            });
        }
        return permissionEventMap.values().stream().toList();
    }

    @Override
    public void createRoleMenuPermission(RolePermissionRequest rolePermissionRequest) throws FinVerusException {
        Role role = getRoleById(rolePermissionRequest.getRoleId());
        if(role == null){
            throw new FinVerusException("Role not found");
        }
        rolePermissionRequest.getPermissionEvents().forEach((permissionEvents)->{
            List<MenuEvent> events = permissionEvents.getEventIds() != null && !permissionEvents.getEventIds().isEmpty() ?
                    getMenuEventListByIds(permissionEvents.getEventIds()) : null;
            Menu menu = getMenuById(permissionEvents.getPermissionId());
            if(menu == null){
                throw new FinVerusException("Menu Not found");
            }
            // create role menu access
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenu(menu);
            roleMenu.setRole(role);
            roleMenu.setActive(1);
            roleMenu.setPermissionCode(generateCode(role.getRoleId(),menu.getProductId(),
                    menu.getMenuCode(), menu.getParentId(), null));
            entityManager.persist(roleMenu);
            if(events != null && !events.isEmpty()){
                events.forEach(event-> {
                    RoleMenuEvent roleMenuEvent = new RoleMenuEvent();
                    roleMenuEvent.setMenuEvent(event);
                    roleMenuEvent.setRoleMenu(roleMenu);
                    roleMenuEvent.setPermissionCode(generateCode(role.getRoleId(), menu.getProductId(),
                            menu.getMenuCode(), menu.getParentId(), event.getEvent().getEventCode()));
                    entityManager.persist(roleMenuEvent);
                });
            }
        });
    }

    @Override
    public Menu getMenuById(long id) throws FinVerusException {
        return entityManager.find(Menu.class, id);
    }

    @Override
    public MenuEvent getMenuEventById(long id) throws FinVerusException {
        return entityManager.find(MenuEvent.class, id);
    }

    @Override
    public List<MenuEvent> getMenuEventListByIds(List<Long> idList) throws FinVerusException {
        final String query = "SELECT m FROM MenuEvent m JOIN FETCH m.event e WHERE m.menuEventId IN (:ids)";
        return entityManager.createQuery(query, MenuEvent.class)
                .setParameter("ids", idList).getResultList();
    }

    private String generateCode(long roleId, int productId, long menuId, long parentMenuId, String eventCode){
        ProductEnum product = ProductEnum.getEnumById(productId);
        return new StringBuilder().append( product != null ? product.getCode() : "00")
                .append(String.format("%05d", roleId))
                .append(String.format("%05d", menuId))
                .append(String.format("%05d", parentMenuId))
                .append(eventCode != null ? eventCode : "000")
                .toString();
    }

    private String generateCode(long roleId, int productId, String menuCode, long parentMenuId, String eventCode){
        ProductEnum product = ProductEnum.getEnumById(productId);
        return new StringBuilder().append( product != null ? product.getCode() : "00")
                .append(String.format("%05d", roleId))
                .append(menuCode != null ? menuCode : "000")
                .append(String.format("%05d", parentMenuId))
                .append(eventCode != null ? eventCode : "000")
                .toString();
    }
}
