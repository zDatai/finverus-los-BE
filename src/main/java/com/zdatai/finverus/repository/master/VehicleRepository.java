package com.zdatai.finverus.repository.master;

import com.zdatai.finverus.model.master.VehicleClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleClass, Long> {}
