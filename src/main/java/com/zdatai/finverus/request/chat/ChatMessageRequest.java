package com.zdatai.finverus.request.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ChatMessageRequest {
    private Long questionId;
    private String responseText;
    private Integer hasAttachment;
    private HashMap<Integer, String> selectedValues;
    private List<String> attachmentNames;
}
