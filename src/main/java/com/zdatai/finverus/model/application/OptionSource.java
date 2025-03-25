package com.zdatai.finverus.model.application;

import com.zdatai.finverus.enums.SourceTypeEnum;
import com.zdatai.finverus.model.AuditCreateUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "option_source")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class OptionSource extends AuditCreateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_source_id", nullable = false, unique = true)
    private Long optionSourceId;

    @Column(name = "source_type", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT STATIC")
    @Enumerated(EnumType.STRING)
    private SourceTypeEnum sourceType;

    @Column(name = "description")
    private String description;

}
