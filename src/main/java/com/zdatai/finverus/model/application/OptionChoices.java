package com.zdatai.finverus.model.application;

import com.zdatai.finverus.enums.SourceTypeEnum;
import com.zdatai.finverus.model.AuditCreateUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "option_choices")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class OptionChoices extends AuditCreateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_source_id", nullable = false, unique = true)
    private Long optionChoiceId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "source_id")
    private OptionSource optionSource;

    @Column(name = "description")
    private String description;

}
