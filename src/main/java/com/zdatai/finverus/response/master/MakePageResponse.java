package com.zdatai.finverus.response.master;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakePageResponse {
    private List<MakeResponse> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private long numberOfElements;
    private int totalPages;
    private boolean last;
}
