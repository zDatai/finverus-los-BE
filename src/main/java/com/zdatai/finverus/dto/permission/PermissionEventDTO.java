package com.zdatai.finverus.dto.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEventDTO {
    private long id;
    private long parentId;
    private int active;
    private int productId;
    private String description;
    private String permissionCode;
    private Set<EventDTO> eventSet = new HashSet<>();
    public boolean hasEvent(long eventId){
        for(EventDTO event : eventSet){
            if(event.getId() == eventId){
                return true;
            }
        }
        return false;
    }
}
