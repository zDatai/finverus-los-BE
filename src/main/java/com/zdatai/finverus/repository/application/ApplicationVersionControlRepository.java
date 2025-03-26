package com.zdatai.finverus.repository.application;

import com.zdatai.finverus.model.application.ApplicationVersionControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationVersionControlRepository extends JpaRepository<ApplicationVersionControl, Long>, JpaSpecificationExecutor<ApplicationVersionControl> {
}
