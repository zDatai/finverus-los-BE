package com.zdatai.finverus.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id", unique = true, nullable = false)
    private long id;
    @Column(name = "parent_id", nullable = false)
    private long parentId;
    @Column(name = "description", length = 200)
    private String description;
    @Column(name = "active", length = 1, nullable = false)
    private int active;
    @Column(name = "product_id", nullable = false)
    private int productId;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PermissionEvent> permissionEvents = new HashSet<>();
}
