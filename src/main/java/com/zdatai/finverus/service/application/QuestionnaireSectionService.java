package com.zdatai.finverus.service.application;

import com.zdatai.finverus.dto.application.PaginatedResponse;
import com.zdatai.finverus.dto.application.SectionDto;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.application.QuestionnaireSection;

public interface QuestionnaireSectionService {
    public void createNew() throws FinVerusException;

    public void updateSection(final Long recordId) throws FinVerusException;

    public PaginatedResponse<SectionDto> getAllSection() throws FinVerusException;

    public SectionDto getSectionById(final Long recordId) throws FinVerusException;

    public QuestionnaireSection getNextSection(final Integer currentSection);
}
