package com.zdatai.finverus.service.impl.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.application.*;
import com.zdatai.finverus.enums.HTTPMethod;
import com.zdatai.finverus.enums.SourceTypeEnum;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.application.ApplicationVersionControl;
import com.zdatai.finverus.model.application.OptionSource;
import com.zdatai.finverus.model.application.PredefinedQuestion;
import com.zdatai.finverus.model.application.QuestionnaireSection;
import com.zdatai.finverus.repository.application.PredefinedQuestionRepository;
import com.zdatai.finverus.request.application.PredefinedQuestionRequest;
import com.zdatai.finverus.service.application.OptionChoicesService;
import com.zdatai.finverus.service.application.PredefinedQuestionService;

import java.util.*;

import com.zdatai.finverus.service.application.QuestionnaireSectionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class PredefinedQuestionServiceimpl implements PredefinedQuestionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredefinedQuestionServiceimpl.class);

    private final PredefinedQuestionRepository predefinedQuestionRepository;

    private final OptionChoicesService optionChoicesService;

    private final QuestionnaireSectionService questionnaireSectionService;

    private final MessageConfig config;

    public PredefinedQuestionServiceimpl(
            final PredefinedQuestionRepository predefinedQuestionRepository,
            final OptionChoicesService optionChoicesService,
            final QuestionnaireSectionService questionnaireSectionService,
            final MessageConfig config) {
        this.predefinedQuestionRepository = predefinedQuestionRepository;
        this.optionChoicesService = optionChoicesService;
        this.questionnaireSectionService = questionnaireSectionService;
        this.config = config;
    }

    @Override
    public void createNew(PredefinedQuestionRequest request, final Long questionnaireSectionId) throws FinVerusException {
        //QuestionnaireSection section = questionnaireSectionService.getSectionById(questionnaireSectionId);

        //OptionSource optionSource = optionSourceRepository.findById(dto.getQuestionnaireSourceId())
                //.orElseThrow(() -> new RuntimeException("Option source not found"));

        /*PredefinedQuestion newQuestion = new PredefinedQuestion();
        newQuestion.setQuestionnaireSection(section);
        newQuestion.setParent(dto.getParent() != null ? dto.getParent() : 0);
        newQuestion.setSequence(dto.getSequence());
        newQuestion.setSkippable(dto.getSkippable() != null ? dto.getSkippable() : 0);
        newQuestion.setInputType(dto.getInputType());
        newQuestion.setResponseType(dto.getResponseType());
        newQuestion.setQuestionnaireSource(optionSource);

        predefinedQuestionRepository.save(newQuestion);*/
    }

    @Override
    public PredefinedQuestionsDto update(Long recordId, PredefinedQuestionRequest request) throws FinVerusException {
        return null;
    }

    @Override
    public PredefinedQuestionsDto getQuestionById(Long recordId) throws FinVerusException {
        PredefinedQuestion predefinedQuestion = predefinedQuestionRepository.findById(recordId)
                .orElseThrow(() -> new FinVerusException("error.record.not.found"));

        return buildPredefinedQuestionsDto(predefinedQuestion);
    }

    @Override
    public List<PredefinedQuestionsDto> getQuestionsByOptionSourceId(Long sourceId) throws FinVerusException {
        return List.of();
    }

    @Override
    public PredefinedQuestionsDto getNextQuestion(Integer currentSequence) {
        Optional<PredefinedQuestion> nextQuestionOpt = predefinedQuestionRepository
                .findFirstBySequenceGreaterThanOrderBySequenceAsc(currentSequence);

        if (nextQuestionOpt.isPresent()) {
            PredefinedQuestion nextQuestion = nextQuestionOpt.get();
            return buildPredefinedQuestionsDto(nextQuestion);
        }

        throw new EntityNotFoundException("No next question found after sequence: " + currentSequence);
    }

    private PredefinedQuestionsDto buildPredefinedQuestionsDto(final PredefinedQuestion predefinedQuestion) {
        return PredefinedQuestionsDto.builder()
                .optionSource(buildOptionSourceDto(predefinedQuestion.getQuestionnaireSource()))
                .parent(predefinedQuestion.getParent())
                .sequence(predefinedQuestion.getSequence())
                .skippable(predefinedQuestion.getSkippable())
                .inputType(predefinedQuestion.getInputType())
                .responseType(predefinedQuestion.getResponseType())
                .question(predefinedQuestion.getQuestion())
                .section(buildSection(predefinedQuestion.getQuestionnaireSection()))
                .recordId(predefinedQuestion.getPredefinedQuestionsId())
                .build();
    }

    private OptionSourceDto buildOptionSourceDto(final OptionSource optionSource) {
        return OptionSourceDto.builder()
                .recordId(optionSource.getOptionSourceId())
                .sourceType(optionSource.getSourceType())
                .choices(buildChoices(optionSource))
                .description(optionSource.getDescription())
                .build();
    }

    private SectionDto buildSection(final QuestionnaireSection section) {
        return SectionDto.builder()
                .recordId(section.getQuestionnaireSectionId())
                .sequence(section.getSequence())
                .sectionName(section.getSectionName()).build();
                //.appVersion(buildVersionControl(section.get))
    }

    private ApplicationVersionControlDto buildVersionControl(final ApplicationVersionControl versionControl) {
        return ApplicationVersionControlDto.builder()
                .applicationVersionControlId(versionControl.getVersionControlId())
                .recordId(versionControl.getApplicationVersionControlId())
                .build();
    }

    private List<OptionChoicesDto> buildChoices(final OptionSource optionSource) {
        if (optionSource.getSourceType() == SourceTypeEnum.STATIC) {
            return optionChoicesService.getChoicesBySourceId(optionSource.getOptionSourceId());
        }

        return Collections.emptyList();
    }

    private List<OptionChoicesDto> fetchApiData(ApiEndPointDto apiEndPoint) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authToken = request.getHeader("Authorization");
        if (authToken != null) {
            headers.add("Authorization", authToken);
        }

        Map<String, String> params = new HashMap<>();
        if (apiEndPoint.getParameters() != null) {
            for (ParameterTextDto param : apiEndPoint.getParameters()) {
                params.put(param.getParam(), param.getValue());
            }
        }

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(params, headers);

        ResponseEntity<String> responseEntity;
        if (apiEndPoint.getHttpMethod() == HTTPMethod.GET) {
            responseEntity = restTemplate.exchange(apiEndPoint.getApiUrl(), HttpMethod.GET, requestEntity, String.class);
        } else {
            responseEntity = restTemplate.exchange(apiEndPoint.getApiUrl(), HttpMethod.POST, requestEntity, String.class);
        }

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            try {
                // Convert response string into list of OptionChoicesDto
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(responseEntity.getBody(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, OptionChoicesDto.class));
            } catch (Exception e) {
                throw new FinVerusException("Failed to parse API response: " + e.getMessage());
            }
        } else {
            throw new FinVerusException("API call failed with status: " + responseEntity.getStatusCode());
        }
    }

    @Override
    public PredefinedQuestionsDto getNextQuestion(Long currentQuestionId, boolean isSkipped) {
        PredefinedQuestion currentQuestion = predefinedQuestionRepository.findById(currentQuestionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        if (isSkipped) {
            QuestionnaireSection currentSection = currentQuestion.getQuestionnaireSection();

            QuestionnaireSection nextSection = questionnaireSectionService.getNextSection(currentSection.getSequence());
            if (nextSection == null) {
                throw new FinVerusException("No more sections available");
            }

            PredefinedQuestion firstQuestionInNextSection = predefinedQuestionRepository
                    .findFirstByQuestionnaireSectionOrderBySequenceAsc(nextSection);

            if (firstQuestionInNextSection == null) {
                throw new FinVerusException("No questions found in the next section");
            }

            return buildPredefinedQuestionsDto(firstQuestionInNextSection);
        } else {
            PredefinedQuestion nextQuestion = predefinedQuestionRepository
                    .findFirstByQuestionnaireSectionAndSequenceGreaterThanOrderBySequenceAsc(
                            currentQuestion.getQuestionnaireSection(), currentQuestion.getSequence());

            if (nextQuestion == null) {
                throw new FinVerusException("No more questions in this section");
            }

            return buildPredefinedQuestionsDto(nextQuestion);
        }
    }

}
