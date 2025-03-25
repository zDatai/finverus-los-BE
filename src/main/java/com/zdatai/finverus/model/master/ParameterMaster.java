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
@Table(name = "parameter_master")
public class ParameterMaster extends AuditModifyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_id")
    private Long parameterId;

    @Column(name = "parameter_name")
    private String parameterName;

    @Column(name = "parameter_type")
    private String parameterType;

    @Column(name = "parameter_status", length = 5)
    private String parameterStatus;

    @Column(name = "overriding_level", length = 50)
    private String overridingLevel;
}
