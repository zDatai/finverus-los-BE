package com.zdatai.finverus.repository.application;

import com.zdatai.finverus.model.application.QuestionnaireSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireSectionRepository extends JpaRepository<QuestionnaireSection, Long>, JpaSpecificationExecutor<QuestionnaireSection> {

    @Query("SELECT qs FROM QuestionnaireSection qs WHERE qs.sequence > :currentSequence ORDER BY qs.sequence ASC")
    QuestionnaireSection findNextSection(@Param("currentSequence") Integer currentSequence);

}
