package com.zdatai.finverus.request.chat;

import com.zdatai.finverus.model.chat.SelectedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ChatMessageRequest {
    private Long questionId;
    private String responseText;
    private Integer hasAttachment;
    private HashMap<Long, String> selectedValues;
    private List<String> attachmentNames;

    public List<SelectedValue> getSelectedValuesAsList() {
        return selectedValues.entrySet().stream()
                .map(entry -> new SelectedValue(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
