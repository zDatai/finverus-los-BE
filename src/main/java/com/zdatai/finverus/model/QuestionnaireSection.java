package com.zdatai.finverus.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "questionnaire_section")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class QuestionnaireSection extends AuditModifyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionnaire_section_id", nullable = false, unique = true)
    private Long questionnaireSectionId;

    @Column(name = "section_name", nullable = false)
    private String sectionName;

    @Column(name = "sequence", nullable = false)
    private Integer sequence;
}
