package com.zdatai.finverus.dto.chat;

import com.zdatai.finverus.dto.CommonAttributes;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SelecteValueDto extends CommonAttributes {
    private Long chatMessagesId;

    private Long referenceId;

    private String value;
}
