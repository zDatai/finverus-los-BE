package com.zdatai.finverus.service.impl.chat;

import com.zdatai.finverus.config.MessageConfig;
import com.zdatai.finverus.dto.application.PredefinedQuestionsDto;
import com.zdatai.finverus.dto.chat.ChatMessagesDto;
import com.zdatai.finverus.enums.ChatProgressStatus;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.chat.ChatMessages;
import com.zdatai.finverus.model.chat.ChatSession;
import com.zdatai.finverus.model.chat.SelectedValue;
import com.zdatai.finverus.repository.chat.ChatMessagesRepository;
import com.zdatai.finverus.repository.chat.ChatSessionRepository;
import com.zdatai.finverus.request.chat.ChatMessageRequest;
import com.zdatai.finverus.request.chat.EditMessageRequest;
import com.zdatai.finverus.response.chat.ChatMessageMultipleResponse;
import com.zdatai.finverus.response.chat.ChatMessagePageResponse;
import com.zdatai.finverus.response.chat.ChatMessageResponse;
import com.zdatai.finverus.service.application.PredefinedQuestionService;
import com.zdatai.finverus.service.chat.ChatMessageService;
import com.zdatai.finverus.utility.FinVerusSpecificationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatMessageServiceImpl.class);

    private final ChatMessagesRepository messagesRepository;

    private final ChatSessionRepository sessionRepository;

    private final PredefinedQuestionService predefinedQuestionService;

    private final MessageConfig config;

    public ChatMessageServiceImpl(
            final ChatMessagesRepository messagesRepository,
            final ChatSessionRepository sessionRepository,
            final PredefinedQuestionService predefinedQuestionService,
            final MessageConfig config) {
        this.messagesRepository = messagesRepository;
        this.sessionRepository = sessionRepository;
        this.predefinedQuestionService = predefinedQuestionService;
        this.config = config;
    }

    @Override
    public ChatMessageResponse createMessage(Long sessionId, ChatMessageRequest messageRequest)
            throws FinVerusException {

        ChatSession session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new FinVerusException("Invalid chat session"));

        ChatMessages chatMessages = buildChatMessages(session, messageRequest);

        messagesRepository.save(chatMessages);

        List<SelectedValue> getSelectValues = messageRequest.getSelectedValuesAsList().stream()
                .peek(e -> e.setChatMessages(chatMessages)) // Modify the object
                .collect(Collectors.toList());


        return ChatMessageResponse.builder()
                .responseStatus("SUCCESS")
                .nextQuestion(predefinedQuestionService
                        .getNextQuestion(chatMessages.getPredefinedQuestion().getSequence()))
                .messageResponse(ChatMessagesDto.builder()
                        .responseText(chatMessages.getResponseText())
                        .question(predefinedQuestionService.getQuestionById(messageRequest.getQuestionId()))
                        .chatProgress(ChatProgressStatus.IN_PROGRESS)
                        .recordId(chatMessages.getChatMessageId())
                        .build())
                .build();
    }

    @Override
    public ChatMessageResponse editMessage(List<EditMessageRequest> messageRequest)
            throws FinVerusException {
        return null;
    }

    @Override
    public ChatMessageMultipleResponse getChatMessagesByListIds(List<Long> recordIds)
            throws FinVerusException {
        return null;
    }

    @Override
    public ChatMessageResponse getChatMessageById(Long chatMessageId) throws FinVerusException {
        return null;
    }

    @Override
    public ChatMessagePageResponse getAllChatMessages(
            final int pageNo, final int pageSize, final String sortBy,
            final Map<String, String> queryParams)
            throws FinVerusException {
        LOGGER.info("Retrieve chat messages info all with pagination (pageNo: {}, pageSize: {}, queryParams: {})",
                pageNo, pageSize, queryParams);

        final Specification<ChatMessages> spec = FinVerusSpecificationUtil
                .getSpecification(queryParams, ChatMessages.class);

        final Sort sort = Sort.by(sortBy).descending();

        final Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        final Page<ChatMessages> chatMessagesPage = messagesRepository.findAll(spec, pageable);

        LOGGER.info("Chat Message data retrieved successfully (pageNo: {}, pageSize: {}, queryParams: {}, data: {})",
                pageNo, pageSize, queryParams, chatMessagesPage.getContent());

        return null;
    }

    private ChatMessages buildChatMessages(final ChatSession session, final ChatMessageRequest request) {
        return ChatMessages.builder()
                .chatSession(session)
                .status(AuditModifyUser.Status.ACTIVE)
                .predefinedQuestion(
                        predefinedQuestionService.getQuestionByIdInternal(request.getQuestionId()))
                .responseText(request.getResponseText())
                .build();
    }
}
