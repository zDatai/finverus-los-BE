package com.zdatai.finverus.repository.chat;

import com.zdatai.finverus.model.chat.SelectedValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedValuesRepository extends JpaRepository<SelectedValue, Long>, JpaSpecificationExecutor<SelectedValue> {
}
