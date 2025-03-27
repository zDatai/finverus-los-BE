package com.zdatai.finverus.model.chat;

import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.application.PredefinedQuestion;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_session_id", nullable = false)
    private ChatSession chatSession;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "predefined_question_id", nullable = true)
    private PredefinedQuestion predefinedQuestion;

    @Column(name = "response_text")
    private String responseText;

    @Column(name = "has_attachment")
    private Integer hasAttachment;

    @OneToMany(mappedBy = "chatMessages", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SelectedValue> selectedValues = new ArrayList<>();
}
