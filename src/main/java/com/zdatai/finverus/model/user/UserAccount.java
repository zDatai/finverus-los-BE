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
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false, length = 20)
    private long userId;
    @Column(name = "username", unique = true, nullable = false, length = 200)
    private String userName;
    @Column(name = "password", length = 200)
    private String password;
    @Column(name = "active", length = 1, nullable = false)
    private int active;
    @Column(name = "expired", length = 1, nullable = false)
    private int expired;
    @Column(name = "locked", length = 1, nullable = false)
    private int locked;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<UserRole> roleSet;
}
