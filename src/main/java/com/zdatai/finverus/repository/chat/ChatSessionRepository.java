package com.zdatai.finverus.repository.chat;

import com.zdatai.finverus.model.chat.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatSessionRepository extends JpaRepository<ChatSession, Long>, JpaSpecificationExecutor<ChatSession> {
}
