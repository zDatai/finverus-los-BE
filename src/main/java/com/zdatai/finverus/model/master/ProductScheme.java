package com.zdatai.finverus.model.master;

import com.zdatai.finverus.enums.ActivityTypeEnum;
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
@Table(name = "produt_scheme")
public class ProductScheme extends AuditModifyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name", length = 50)
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private ActivityTypeEnum activityType;

    @Column(name = "product_type",length = 50)
    private String productType;

    @Column(name = "account_no_prefix",length = 50)
    private String accountNoPrefix;
}
