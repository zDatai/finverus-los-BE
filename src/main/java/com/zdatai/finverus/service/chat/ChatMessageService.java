package com.zdatai.finverus.service.chat;

import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.request.chat.ChatMessageRequest;
import com.zdatai.finverus.request.chat.EditMessageRequest;
import com.zdatai.finverus.response.chat.ChatMessageMultipleResponse;
import com.zdatai.finverus.response.chat.ChatMessagePageResponse;
import com.zdatai.finverus.response.chat.ChatMessageResponse;

import java.util.List;
import java.util.Map;

public interface ChatMessageService {
    public ChatMessageResponse createMessage(final Long sessionId, final ChatMessageRequest messageRequest)
            throws FinVerusException;

    public ChatMessageResponse editMessage(List<EditMessageRequest> messageRequest)
            throws FinVerusException;

    public ChatMessageMultipleResponse getChatMessagesByListIds(final List<Long> recordIds)
        throws FinVerusException;

    public ChatMessageResponse getChatMessageById(final Long chatMessageId) throws FinVerusException;

    public ChatMessagePageResponse getAllChatMessages(
            final int pageNo, final int pageSize, final String sortBy,
            final Map<String, String> queryParams) throws FinVerusException;
}
