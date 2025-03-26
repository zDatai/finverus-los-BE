package com.zdatai.finverus.controller.application;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.application.PredefinedQuestionsDto;
import com.zdatai.finverus.service.application.PredefinedQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/{version}/public/predefined-questions")
@RestController
public class PredefinedQuestionsController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PredefinedQuestionsController.class);

    private final PredefinedQuestionService predefinedQuestionService;

    private final MessageConfig config;

    public PredefinedQuestionsController(
            final PredefinedQuestionService predefinedQuestionService,
            final MessageConfig config) {
        this.predefinedQuestionService = predefinedQuestionService;
        this.config = config;
    }

    @GetMapping("/next/{currentSequenceNo}")
    public ResponseEntity<PredefinedQuestionsDto> nextQuestion(
            @PathVariable(value = "currentSequenceNo", required = true) final Integer currentSequenceNo) {
        return ResponseEntity.ok(predefinedQuestionService.getNextQuestion(currentSequenceNo));
    }
}
