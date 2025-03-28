package com.zdatai.finverus.service.application;

import com.zdatai.finverus.dto.application.PredefinedQuestionsDto;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.application.PredefinedQuestion;
import com.zdatai.finverus.request.application.PredefinedQuestionRequest;

import java.util.List;

public interface PredefinedQuestionService {

    public void createNew(PredefinedQuestionRequest request, Long questionnaireSectionId) throws FinVerusException;

    public PredefinedQuestionsDto update(final Long recordId, final PredefinedQuestionRequest request)
            throws FinVerusException;

    public PredefinedQuestionsDto getQuestionById(final Long recordId) throws FinVerusException;

    PredefinedQuestion getQuestionByIdInternal(Long recordId) throws FinVerusException;

    public List<PredefinedQuestionsDto> getQuestionsByOptionSourceId(final Long sourceId) throws FinVerusException;

    public PredefinedQuestionsDto getNextQuestion(final Integer currentSequence);

    PredefinedQuestionsDto getNextQuestion(Long currentQuestionId, boolean isSkipped);
}
