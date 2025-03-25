package com.zdatai.finverus.model.application;

import com.zdatai.finverus.enums.UIComponentTypeEnum;
import com.zdatai.finverus.model.AuditModifyUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "predefined_question")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class PredefinedQuestion extends AuditModifyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "predefined_question_id", nullable = false, unique = true)
    private Long predefinedQuestionsId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "questionnaire_section_id")
    private QuestionnaireSection questionnaireSection;

    @Column(name = "parent", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Integer parent;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;

    @Column(name = "skippable", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Integer skippable;

    @Column(name = "input_type", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT TEXT")
    @Enumerated(EnumType.STRING)
    private UIComponentTypeEnum inputType;

    @Column(name = "response_type", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT TEXT")
    @Enumerated(EnumType.STRING)
    private UIComponentTypeEnum responseType;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "questionnaire_source_id")
    private OptionSource questionnaireSource;
}
