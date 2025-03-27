package com.zdatai.finverus.repository.master;

import com.zdatai.finverus.model.master.Model;
import com.zdatai.finverus.response.master.ModelResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ModelRepository extends JpaRepository<Model,Long> {
    @Query("SELECT new com.zdatai.finverus.response.master.ModelResponse(m.modelId, m.model, m.make.makeId," +
            " m.assetCategory.assetCategoryId) FROM Model m ORDER BY m.model asc")
    Page<ModelResponse> getAllModels(Pageable pageable);
}
