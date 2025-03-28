package com.zdatai.finverus.model.application;

import com.zdatai.finverus.model.AuditModifyUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "question_validator_mapper")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class QuestionValidatorMapper extends AuditModifyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapper_id")
    private Long mapperId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private PredefinedQuestion predefinedQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "validation_id")
    private ValidationRule validationRule;

    @Column(name = "validator_message")
    private String validatorMessage;
}
