package com.zdatai.finverus.controller.api.user;

import com.zdatai.finverus.config.FinVerusAuthToken;
import com.zdatai.finverus.dto.user.LoginResultDTO;
import com.zdatai.finverus.exception.FinVerusException;
import com.zdatai.finverus.request.user.LoginRequest;
import com.zdatai.finverus.service.user.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(value = "/api/${version}/user")
@RestController
@Tag( name = "Authentication", description = "Endpoints for managing authentication")
public class AuthenticationRestController {

    @Autowired
    private AuthenticationService authenticationService;

    @Operation( summary = "Retrieve CSRF", tags = {"Authentication"})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved CSRF",
                    content = @Content(schema = @Schema(implementation = CsrfToken.class))
            )
    })
    @GetMapping(value = "/csrf")
    public CsrfToken getCSRFToken(CsrfToken csrfToken)throws FinVerusException {
        log.info("** Csrf Token created ***");
        return csrfToken;
    }

    @Operation( summary = "Retrieve Auth Token", tags = {"Authentication"})
    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Successfully retrieved Auth Token", content = @Content(schema = @Schema(implementation = LoginResultDTO.class)) )
    })
    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, HttpSession session, HttpServletRequest request, HttpServletResponse response)throws FinVerusException {
        LoginResultDTO result = authenticationService.authenticate(loginRequest.getUsername(), loginRequest.getPasscode(), session, request, response);
        return new ResponseEntity<>(com.zdatai.finverus.response.ApiResponse.success(result), HttpStatus.OK);
    }

    @Operation( summary = "******TEST*******", tags = {"Authentication"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "**********TEST******" )
    })
    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> test(@RequestAttribute("authSession") FinVerusAuthToken token, HttpSession session)throws FinVerusException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach( (auth) -> {
            log.info("## User {} Has Permission {} Session : {} ##",authentication.getPrincipal(),auth.getAuthority(), session.getId());
        });
        return new ResponseEntity<>(com.zdatai.finverus.response.ApiResponse.success(null), HttpStatus.OK);
    }

}
