package com.zdatai.finverus.model.application;

import com.zdatai.finverus.enums.HTTPMethod;
import com.zdatai.finverus.model.AuditModifyUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table(name = "api_endpoint")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class ApiEndpoint extends AuditModifyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "api_endpoint_id", nullable = false, unique = true)
    private Long apiEndpointId;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "questionnaire_source_id")
    private OptionSource questionnaireSource;

    @Column(name = "api_name", nullable = false)
    private String apiName;

    @Column(name = "api_url", nullable = false)
    private String apiUrl;

    @Column(name = "http_method", nullable = false)
    @Enumerated(EnumType.STRING)
    private HTTPMethod httpMethod;

    @Column(name = "http_text", nullable = false)
    private String headerText;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "apiEndpoint", orphanRemoval = true)
    private List<ParameterText> parameterText;
}
