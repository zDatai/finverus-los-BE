package com.zdatai.finverus.filter;

import com.zdatai.finverus.config.FinVerusAuthEntryPointHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfigFilter {

    private final FinVerusAuthEntryPointHandler finverusAuthEntryPointHandler;
    private final FinVerusRequestFilter requestFilter;

    public SecurityConfigFilter(FinVerusAuthEntryPointHandler finverusAuthEntryPointHandler,
                                FinVerusRequestFilter requestFilter) {
        this.finverusAuthEntryPointHandler = finverusAuthEntryPointHandler;
        this.requestFilter = requestFilter;
    }

    @Value("${version}")
    private String version;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf ->
                csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository())
        );

        httpSecurity.authorizeHttpRequests(auth ->
                auth.requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html",
                                "/api/" + version + "/**"
                        ).permitAll()
                        .anyRequest().authenticated()
        ).exceptionHandling(
                e ->
                        e.authenticationEntryPoint(finverusAuthEntryPointHandler));

        httpSecurity.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::migrateSession)
                        .enableSessionUrlRewriting(false)
        );

        httpSecurity.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
