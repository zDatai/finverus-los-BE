package com.zdatai.finverus.repository.master;

import com.zdatai.finverus.model.master.ProductScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSchemeRepository extends JpaRepository<ProductScheme,Long> {
}
