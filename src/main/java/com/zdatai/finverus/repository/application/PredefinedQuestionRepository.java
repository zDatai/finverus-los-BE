package com.zdatai.finverus.repository.application;

import com.zdatai.finverus.model.application.PredefinedQuestion;
import com.zdatai.finverus.model.application.QuestionnaireSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PredefinedQuestionRepository extends JpaRepository<PredefinedQuestion, Long>, JpaSpecificationExecutor<PredefinedQuestion> {
    Optional<PredefinedQuestion> findFirstBySequenceGreaterThanOrderBySequenceAsc(final Integer currentSequence);

    PredefinedQuestion findFirstByQuestionnaireSectionOrderBySequenceAsc(final QuestionnaireSection nextSection);

    PredefinedQuestion findFirstByQuestionnaireSectionAndSequenceGreaterThanOrderBySequenceAsc(
            final QuestionnaireSection questionnaireSection, final Integer sequence);
}
