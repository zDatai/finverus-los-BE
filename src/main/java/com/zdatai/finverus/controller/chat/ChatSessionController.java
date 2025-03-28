package com.zdatai.finverus.controller.chat;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.application.PredefinedQuestionsDto;
import com.zdatai.finverus.service.application.PredefinedQuestionService;
import com.zdatai.finverus.service.application.QuestionnaireSectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequestMapping(value = "/api/{version}/public/chat-session")
@RestController
public class ChatSessionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatSessionController.class);

    private final QuestionnaireSectionService questionnaireSectionService;

    private final PredefinedQuestionService predefinedQuestionService;

    private final MessageConfig config;

    public ChatSessionController(
            final QuestionnaireSectionService questionnaireSectionService,
            final PredefinedQuestionService predefinedQuestionService,
            final MessageConfig config) {
        this.questionnaireSectionService = questionnaireSectionService;
        this.predefinedQuestionService = predefinedQuestionService;
        this.config = config;
    }

    @GetMapping(value = "/start")
    public ResponseEntity<PredefinedQuestionsDto> startSession() {
        return ResponseEntity.ok(predefinedQuestionService.getNextQuestion(BigDecimal.ZERO.intValue()));
    }
}
