package com.zdatai.finverus.controller.chat;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.application.PredefinedQuestionsDto;
import com.zdatai.finverus.dto.chat.ChatMessagesDto;
import com.zdatai.finverus.request.chat.ChatMessageRequest;
import com.zdatai.finverus.response.chat.ChatMessageResponse;
import com.zdatai.finverus.service.application.PredefinedQuestionService;
import com.zdatai.finverus.service.application.QuestionnaireSectionService;
import com.zdatai.finverus.service.chat.ChatMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequestMapping(value = "/api/{version}/public/chat-message")
@RestController
public class ChatMessageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChatMessageController.class);

    private final ChatMessageService chatMessageService;

    private final PredefinedQuestionService predefinedQuestionService;

    private final MessageConfig config;

    public ChatMessageController(
            final ChatMessageService chatMessageService,
            final PredefinedQuestionService predefinedQuestionService,
            final MessageConfig config) {
        this.chatMessageService = chatMessageService;
        this.predefinedQuestionService = predefinedQuestionService;
        this.config = config;
    }

    @GetMapping(value = "/chat")
    public ResponseEntity<PredefinedQuestionsDto> startSession() {
        return ResponseEntity.ok(predefinedQuestionService.getNextQuestion(BigDecimal.ZERO.intValue()));
    }

    @PostMapping(value = "/create/{sessionId}")
    private ResponseEntity<ChatMessageResponse> createMessage(
            @PathVariable(name = "sessionId", required = true) final Long sessionId,
            @RequestParam(value = "sortDir") final ChatMessageRequest messageRequest) {

        ChatMessageResponse response = chatMessageService.createMessage(sessionId, messageRequest);

        return ResponseEntity.ok(response);
    }
}
