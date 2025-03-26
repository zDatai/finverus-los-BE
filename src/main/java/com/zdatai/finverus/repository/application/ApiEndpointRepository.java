package com.zdatai.finverus.repository.application;

import com.zdatai.finverus.model.application.ApiEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiEndpointRepository extends JpaRepository<ApiEndpoint, Long>, JpaSpecificationExecutor<ApiEndpoint> {
}
