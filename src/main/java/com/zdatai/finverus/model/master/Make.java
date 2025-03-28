package com.zdatai.finverus.model.master;

import com.zdatai.finverus.model.AuditModifyUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "make",
        uniqueConstraints = {@UniqueConstraint(columnNames = "make")})
public class Make extends AuditModifyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "make_id")
    private Long makeId;

    @Column(name = "make", unique = true)
    private String make;



}
