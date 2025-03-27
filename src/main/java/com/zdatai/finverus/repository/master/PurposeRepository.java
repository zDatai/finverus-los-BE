package com.zdatai.finverus.repository.master;

import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.Purpose;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurposeRepository extends JpaRepository<Purpose, Long> {
    Page<Purpose> findByStatus(AuditModifyUser.Status status, Pageable pageable);
}
