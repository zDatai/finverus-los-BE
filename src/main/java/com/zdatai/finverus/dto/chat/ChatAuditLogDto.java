package com.zdatai.finverus.dto.chat;

import com.zdatai.finverus.dto.CommonAttributes;
import com.zdatai.finverus.dto.application.ApplicationVersionControlDto;
import com.zdatai.finverus.enums.ChatProgressStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ChatAuditLogDto extends CommonAttributes {
    private String username;
    private Long userId;
    private ApplicationVersionControlDto appVersion;
    private ChatProgressStatus chatProgress;
}
