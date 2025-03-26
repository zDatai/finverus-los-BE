package com.zdatai.finverus.repository.application;

import com.zdatai.finverus.model.application.OptionSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionSourceRepository extends JpaRepository<OptionSource, Long>, JpaSpecificationExecutor<OptionSource> {
}
