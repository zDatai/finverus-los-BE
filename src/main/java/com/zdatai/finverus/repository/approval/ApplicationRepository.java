package com.zdatai.finverus.repository.approval;

import com.zdatai.finverus.model.approval.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
