package com.zdatai.finverus.model.chat;

import com.zdatai.finverus.model.AuditModifyUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "selected_values")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class SelectedValue extends AuditModifyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selected_value_id", nullable = false, unique = true)
    private Long selectedValueId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "message_id", nullable = false)
    private ChatMessages chatMessages;

    @Column(name = "reference_record_id", nullable = true)
    private Long referenceId;

    @Column(name = "value", nullable = false)
    private String value;

    public SelectedValue(Long key, String value) {
        this.referenceId = key;
        this.value = value;
    }
}

