package com.zdatai.finverus.repository.master;

import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.AssetCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetCategoryRepository extends JpaRepository<AssetCategory, Long> {

    Page<AssetCategory> findByStatus(AuditModifyUser.Status status, Pageable pageable);
}
