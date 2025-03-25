package com.zdatai.finverus.model.chat;

import com.zdatai.finverus.enums.UIComponentTypeEnum;
import com.zdatai.finverus.model.AuditCreateUser;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.application.ApplicationVersionControl;
import com.zdatai.finverus.model.application.PredefinedQuestion;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "chat_audit_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ChatAuditLog extends AuditCreateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_session_id", nullable = false, unique = true)
    private Long chatAuditLogId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_message_id", nullable = false)
    private ChatMessages chatMessages;

    @Column(name = "previous_response")
    private String previousResponse;

    @Column(name = "new_response")
    private String newResponse;

    @Column(name = "response_type", nullable = true)
    @Enumerated(EnumType.STRING)
    private UIComponentTypeEnum responseType;

}
