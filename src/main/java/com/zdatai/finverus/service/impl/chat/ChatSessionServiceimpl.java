package com.zdatai.finverus.service.impl.chat;

import com.zdatai.finverus.dto.chat.ChatSessionDto;
import com.zdatai.finverus.enums.ChatProgressStatus;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.model.chat.ChatSession;
import com.zdatai.finverus.repository.application.ApplicationVersionControlRepository;
import com.zdatai.finverus.repository.chat.ChatSessionRepository;
import com.zdatai.finverus.repository.permission.PermissionDAO;
import com.zdatai.finverus.service.chat.ChatSessionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ChatSessionServiceimpl implements ChatSessionService {
    private final ChatSessionRepository sessionRepository;
    private final ApplicationVersionControlRepository versionControlRepository;
    private final PermissionDAO permissionDAO;

    public ChatSessionServiceimpl(
            ChatSessionRepository sessionRepository,
            ApplicationVersionControlRepository versionControlRepository, PermissionDAO permissionDAO) {
        this.sessionRepository = sessionRepository;
        this.versionControlRepository = versionControlRepository;
        this.permissionDAO = permissionDAO;
    }

    @Override
    public ChatSessionDto createSession() throws FinVerusException {
        //ChatSession.builder()
                //.versionControl()
                //.userAccount(permissionDAO.(SecurityContextHolder.getContext().getAuthentication().getName())
                //.chatStatus(ChatProgressStatus.IN_PROGRESS)
        //return sessionRepository.save();
        return new ChatSessionDto();
    }
}
