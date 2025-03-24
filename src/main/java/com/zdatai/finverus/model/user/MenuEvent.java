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
@Table(name = "menu_event")
public class MenuEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_event_id", nullable = false, unique = true)
    private long menuEventId;
    @ManyToOne
    @JoinColumn(name = "menu", referencedColumnName = "menu_id")
    private Menu menu;
    @ManyToOne
    @JoinColumn(name = "event", referencedColumnName = "event_id")
    private Event event;
    @OneToMany(mappedBy = "menuEvent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RoleMenuEvent> roleMenuEventSet;
}
