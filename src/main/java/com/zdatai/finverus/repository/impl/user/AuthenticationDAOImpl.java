package com.zdatai.finverus.repository.impl.user;

import com.zdatai.finverus.dto.user.FinVerusUserDetail;
import com.zdatai.finverus.dto.user.Permission;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.user.RoleMenu;
import com.zdatai.finverus.model.user.RoleMenuEvent;
import com.zdatai.finverus.model.user.UserAccount;
import com.zdatai.finverus.repository.user.AuthenticationDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class AuthenticationDAOImpl implements AuthenticationDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public FinVerusUserDetail getUserAccountByName(String userName) throws FinVerusException {
        Map<Long, FinVerusUserDetail> userAccountDTOMap = new HashMap<>();
        final String userAccountQuery = "SELECT a, m, e FROM UserAccount a LEFT JOIN a.roleSet rs" +
                " LEFT JOIN rs.role r LEFT JOIN r.roleMenuSet m LEFT JOIN m.roleMenuEventSet e " +
                " WHERE a.userName= :userName";
        List<Object> list = entityManager.createQuery(userAccountQuery, Object.class)
                .setParameter("userName", userName)
                .getResultList();
        if(list == null || list.isEmpty()){
            log.error("** User Name : {} not found", userName);
            throw new FinVerusException("Invalid username or password");
        }
        list.forEach((row)->{
            Object[] cols = (Object[]) row;
            UserAccount userAccount = cols[0] != null ? (UserAccount) cols[0] : null;
            RoleMenu roleMenu = cols[1] != null ? (RoleMenu) cols[1] : null;
            RoleMenuEvent roleMenuEvent = cols[2] != null ? (RoleMenuEvent) cols[2] : null;
            if(userAccount != null){
                FinVerusUserDetail userAccountDTO = null;
                if(userAccountDTOMap.get(userAccount.getUserId()) != null){
                    userAccountDTO = userAccountDTOMap.get(userAccount.getUserId());
                }else{
                    userAccountDTO = new FinVerusUserDetail();
                    userAccountDTO.setUserName(userAccount.getUserName());
                    userAccountDTO.setPasscode(userAccount.getPassword());
                    userAccountDTO.setActive(userAccount.getActive() == 1);
                    userAccountDTO.setAccountNonLocked(userAccount.getLocked() == 0);
                    userAccountDTO.setAccountNonExpired(userAccount.getExpired() == 0);
                    userAccountDTO.setCredentialNonExpired(userAccount.getExpired() == 0);
                    userAccountDTO.setUserId(userAccount.getUserId());
                }
                if(roleMenu != null){
                    Permission permission = new Permission();
                    permission.setPermissionCode(roleMenu.getPermissionCode());
                    if(userAccountDTO.getPermissionSet() == null){
                        Set<Permission> permissionSet = new HashSet<>();
                        permissionSet.add(permission);
                        userAccountDTO.setAuthorities(permissionSet);
                    }else{
                        userAccountDTO.getPermissionSet().add(permission);
                    }
                }
                if(roleMenuEvent != null){
                    Permission permission = new Permission();
                    permission.setPermissionCode(roleMenuEvent.getPermissionCode());
                    if(userAccountDTO.getPermissionSet() == null){
                        Set<Permission> permissionSet = new HashSet<>();
                        permissionSet.add(permission);
                        userAccountDTO.setAuthorities(permissionSet);
                    }else{
                        userAccountDTO.getPermissionSet().add(permission);
                    }
                }
                userAccountDTOMap.put(userAccount.getUserId(), userAccountDTO);
            }
        });
        return userAccountDTOMap.values().stream().toList().get(0);
    }
}

