package com.zdatai.finverus.response.chat;

import com.zdatai.finverus.dto.application.PredefinedQuestionsDto;
import com.zdatai.finverus.dto.chat.ChatMessagesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageResponse {
    private PredefinedQuestionsDto nextQuestion;
    private ChatMessagesDto messageResponse;
    private String responseStatus;
    private String validationMessage;
}
