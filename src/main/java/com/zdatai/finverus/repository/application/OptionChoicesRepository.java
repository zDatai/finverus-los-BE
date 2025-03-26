package com.zdatai.finverus.repository.application;

import com.zdatai.finverus.model.application.OptionChoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionChoicesRepository extends JpaRepository<OptionChoices, Long>, JpaSpecificationExecutor<OptionChoices> {
    List<OptionChoices> findByOptionSourceOptionSourceId(final Long sourceId);
}
