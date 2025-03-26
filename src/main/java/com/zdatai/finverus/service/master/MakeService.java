package com.zdatai.finverus.service.master;

import com.zdatai.finverus.response.master.MakePageResponse;
import com.zdatai.finverus.response.master.MakeResponse;

import java.util.Map;

public interface MakeService {
    MakePageResponse getAllMake(int pageNo, int pageSize, String sortBy, String sortDir, Map<String, String> queryParams);

}
