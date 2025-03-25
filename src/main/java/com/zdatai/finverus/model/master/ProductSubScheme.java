package com.zdatai.finverus.model.master;

import com.zdatai.finverus.enums.ProductSubSchemeStatusEnum;
import com.zdatai.finverus.model.AuditModifyUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produt_sub_scheme")
public class ProductSubScheme extends AuditModifyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheme_id")
    private Long schemeId;

    @Column(name = "scheme_name", length = 50)
    private String schemeName;

    @Column(name = "effective_date")
    private LocalDate effectiveDate;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "schema_status")
    private ProductSubSchemeStatusEnum schemaStatus;
}
