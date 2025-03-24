package com.zdatai.finverus.service.user;

import com.zdatai.finverus.dto.user.LoginResultDTO;
import com.zdatai.finverus.exception.FinVerusException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public interface AuthenticationService {
    LoginResultDTO authenticate(String userName, String password, HttpSession session, HttpServletRequest request, HttpServletResponse response)throws FinVerusException;
}
