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
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id",unique = true, nullable = false)
    private long roleId;
    @Column(name = "role_name", length = 200)
    private String roleName;
    @Column(name = "active", length = 1, nullable = false)
    private int active;
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<RoleMenu> roleMenuSet;
}
