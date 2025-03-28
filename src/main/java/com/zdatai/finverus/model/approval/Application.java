package com.zdatai.finverus.model.approval;

import com.zdatai.finverus.enums.ApplicationStatusEnum;
import com.zdatai.finverus.enums.ApplicationTypeEnum;
import com.zdatai.finverus.enums.CustomerTypeEnum;
import com.zdatai.finverus.model.master.Agent;
import com.zdatai.finverus.model.master.ProductScheme;
import com.zdatai.finverus.model.master.ProductSubScheme;
import com.zdatai.finverus.model.user.UserAccount;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Table(name = "application")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", nullable = false, unique = true)
    private Long applicationId;

    @Column(name = "application_number")
    private Integer applicationNumber;

    @Column(name = "application_type")
    @Enumerated(EnumType.STRING)
    private ApplicationTypeEnum applicationType;

    @Column(name = "application_status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatusEnum applicationStatus;

    @Column(name = "application_amount")
    private BigDecimal applicationAmount;

    @Column(name = "total_advance_amount")
    private BigDecimal totalAdvanceAmount;

    @Column(name = "customer_type")
    @Enumerated(EnumType.STRING)
    private CustomerTypeEnum customerType;

    @JoinColumn(name = "product_scheme_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductScheme productScheme;

    @JoinColumn(name = "product_sub_scheme_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductSubScheme productSubScheme;

    @JoinColumn(name = "agent_id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Agent agent;

    @JoinColumn(name = "introducer_id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount userAccount;

    @Column(name = "customer_id")
    private Long customerId;
}
