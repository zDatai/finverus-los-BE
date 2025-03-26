package com.zdatai.finverus.repository.master;

import com.zdatai.finverus.model.master.Make;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeRepository extends JpaRepository<Make,Long> {
    Page<Make> findAll(Specification<Make> spec, Pageable pageable);
}
