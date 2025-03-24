package com.zdatai.finverus.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_permission_event")
public class RolePermissionEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_permission_event_id")
    private long rolePermissionEventId;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = true)
    private Event event;
    @Column(name = "permission_code", unique = true, nullable = false, length = 30)
    private String permissionCode;
    @Column(name = "active", length = 1, nullable = false)
    private int active;
}
