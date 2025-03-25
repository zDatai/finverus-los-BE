package com.zdatai.finverus.model.chat;

import com.zdatai.finverus.enums.ChatProgressStatus;
import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.application.ApplicationVersionControl;
import com.zdatai.finverus.model.user.UserAccount;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "chat_session")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ChatSession extends AuditModifyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_session_id", nullable = false, unique = true)
    private Long chatSessionId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount userAccount;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "version_control_id", nullable = false)
    private ApplicationVersionControl versionControl;

    @Enumerated(EnumType.STRING)
    @Column(name = "chat_status")
    private ChatProgressStatus chatStatus;

}
