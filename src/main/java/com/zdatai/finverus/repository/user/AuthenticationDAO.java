package com.zdatai.finverus.repository.user;

import com.zdatai.finverus.dto.user.FinVerusUserDetail;
import com.zdatai.finverus.exception.FinVerusException;

public interface AuthenticationDAO {
    FinVerusUserDetail getUserAccountByName(String userName)throws FinVerusException;
}
