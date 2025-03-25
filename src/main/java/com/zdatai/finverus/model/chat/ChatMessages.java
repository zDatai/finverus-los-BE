package com.zdatai.finverus.model.chat;

import com.zdatai.finverus.enums.ChatProgressStatus;
import com.zdatai.finverus.enums.UIComponentTypeEnum;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.application.ApplicationVersionControl;
import com.zdatai.finverus.model.application.PredefinedQuestion;
import com.zdatai.finverus.model.user.UserAccount;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "chat_messages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ChatMessages extends AuditModifyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_messages_id", nullable = false, unique = true)
    private Long chatMessageId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_session_id", nullable = false)
    private ChatSession chatSession;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "version_control_id", nullable = true)
    private ApplicationVersionControl versionControl;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "predefined_question_id", nullable = false)
    private PredefinedQuestion predefinedQuestion;

    @Column(name = "response_text")
    private String responseText;

    @Column(name = "response_type", nullable = true)
    @Enumerated(EnumType.STRING)
    private UIComponentTypeEnum responseType;

}
