package com.zdatai.finverus.service.impl.application;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.application.PaginatedResponse;
import com.zdatai.finverus.dto.application.SectionDto;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.application.QuestionnaireSection;
import com.zdatai.finverus.repository.application.QuestionnaireSectionRepository;
import com.zdatai.finverus.service.application.QuestionnaireSectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class QuestionnaireSectionServiceImpl implements QuestionnaireSectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionnaireSectionServiceImpl.class);

    private final QuestionnaireSectionRepository questionnaireSectionRepository;

    private final MessageConfig config;

    public QuestionnaireSectionServiceImpl(
            final QuestionnaireSectionRepository questionnaireSectionRepository,
            final MessageConfig config) {
        this.questionnaireSectionRepository = questionnaireSectionRepository;
        this.config = config;
    }

    @Override
    public void createNew() throws FinVerusException {

    }

    @Override
    public void updateSection(Long recordId) throws FinVerusException {

    }

    @Override
    public PaginatedResponse<SectionDto> getAllSection() throws FinVerusException {
        return null;
    }

    @Override
    public SectionDto getSectionById(Long recordId) throws FinVerusException {
        return null;
    }

    @Override
    public QuestionnaireSection getNextSection(Integer currentSection) {
        return questionnaireSectionRepository.findNextSection(currentSection);
    }
}
