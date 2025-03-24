package com.zdatai.finverus.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_permission")
public class PermissionEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_event_id")
    private long permissionEventId;
    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
