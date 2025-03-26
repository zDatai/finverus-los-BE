package com.zdatai.finverus.repository.master;

import com.zdatai.finverus.model.master.InsuranceInstitute;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceInstituteRepository extends JpaAttributeConverter<InsuranceInstitute,Long> {}
