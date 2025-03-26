package com.zdatai.finverus.repository.master;

import com.zdatai.finverus.model.AuditModifyUser;
import com.zdatai.finverus.model.master.ProductScheme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSchemeRepository extends JpaRepository<ProductScheme,Long> {
    Page<ProductScheme> findByStatus(AuditModifyUser.Status status, Pageable pageable);

    boolean existsProductSchemeByProductId(Long productId);
}
