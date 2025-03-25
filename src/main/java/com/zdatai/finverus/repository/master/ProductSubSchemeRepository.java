package com.zdatai.finverus.repository.master;

import com.zdatai.finverus.model.master.ProductSubScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSubSchemeRepository extends JpaRepository<ProductSubScheme,Long> {
}
