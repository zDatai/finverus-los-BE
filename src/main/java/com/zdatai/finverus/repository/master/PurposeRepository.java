package com.zdatai.finverus.repository.master;

import com.zdatai.finverus.model.master.Purpose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurposeRepository extends JpaRepository<Purpose, Long> {}
