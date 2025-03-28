package com.zdatai.finverus.repository.user;

import com.zdatai.finverus.model.user.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
