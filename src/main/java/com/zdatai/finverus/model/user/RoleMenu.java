package com.zdatai.finverus.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_menu")
public class RoleMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_menu_id", unique = true, nullable = false)
    private long roleMenuId;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
    private Menu menu;
    @Column(name = "active")
    private int active;
    @Column(name = "permission_code", unique = true)
    private String permissionCode;

    @OneToMany(mappedBy = "roleMenu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RoleMenuEvent> roleMenuEventSet;
}
