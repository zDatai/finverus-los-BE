package com.zdatai.finverus.model.master;

import com.zdatai.finverus.model.AuditModifyUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "make")
public class Make extends AuditModifyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "make_id")
    private Long makeId;

    @Column(name = "make")
    private String make;

}
