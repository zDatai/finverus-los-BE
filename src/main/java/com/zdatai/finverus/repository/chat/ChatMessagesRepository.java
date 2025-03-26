package com.zdatai.finverus.repository.chat;

import com.zdatai.finverus.model.chat.ChatMessages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessagesRepository extends JpaRepository<ChatMessages, Long>, JpaSpecificationExecutor<ChatMessages> {
}
