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
@Table(name = "insurance_institute")
public class InsuranceInstitute extends AuditModifyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_institute_id")
    private Long insuranceInstituteId;

    @Column(name = "insurance_institute_name")
    private String insuranceInstituteName;
}
