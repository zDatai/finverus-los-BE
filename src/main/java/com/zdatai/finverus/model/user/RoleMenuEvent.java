package com.zdatai.finverus.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_menu_event")
public class RoleMenuEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_menu_event_id")
    private long roleMenuEventId;
    @ManyToOne
    @JoinColumn(name = "role_menu_id", referencedColumnName = "role_menu_id")
    private RoleMenu roleMenu;
    @ManyToOne
    @JoinColumn(name = "menu_event_id", referencedColumnName = "menu_event_id")
    private MenuEvent menuEvent;
    @Column(name = "permission_code", unique = true)
    private String permissionCode;
}
