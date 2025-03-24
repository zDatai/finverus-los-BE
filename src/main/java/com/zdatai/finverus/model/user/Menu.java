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
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id", unique = true, nullable = false)
    private long menuId;
    @Column(name = "parent_id")
    private long parentId;
    @Column(name = "menu_code", unique = true, nullable = false)
    private String menuCode;
    @Column(name = "description", length = 200)
    private String menuDescription;
    @Column(name = "product")
    private int productId;
    @Column(name = "active")
    private int active;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<MenuEvent> menuEventSet;
}
