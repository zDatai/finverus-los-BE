package com.zdatai.finverus.response.chat;

import com.zdatai.finverus.dto.application.PredefinedQuestionsDto;
import com.zdatai.finverus.dto.chat.ChatMessagesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageMultipleResponse {
    private PredefinedQuestionsDto nextQuestion;
    private List<ChatMessagesDto> messageResponse;
}
