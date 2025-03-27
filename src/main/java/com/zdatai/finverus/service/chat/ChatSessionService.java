package com.zdatai.finverus.service.chat;

import com.zdatai.finverus.dto.chat.ChatSessionDto;
import com.zdatai.finverus.exception.FinVerusException;

public interface ChatSessionService {
    public ChatSessionDto createSession() throws FinVerusException;

}
