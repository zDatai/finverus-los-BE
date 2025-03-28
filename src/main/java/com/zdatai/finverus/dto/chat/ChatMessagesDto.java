package com.zdatai.finverus.dto.chat;

import com.zdatai.finverus.dto.CommonAttributes;
import com.zdatai.finverus.dto.application.PredefinedQuestionsDto;
import com.zdatai.finverus.enums.ChatProgressStatus;
import com.zdatai.finverus.enums.UIComponentTypeEnum;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ChatMessagesDto extends CommonAttributes {
    private Long chatSessionId;
    private PredefinedQuestionsDto question;
    private String responseText;
    private ChatProgressStatus chatProgress;
    private ChatAuditLogDto auditLog;
    private UIComponentTypeEnum responseType;
    private List<SelecteValueDto> selectedValues;
}
