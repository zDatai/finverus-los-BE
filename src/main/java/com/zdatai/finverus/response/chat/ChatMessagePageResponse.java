package com.zdatai.finverus.response.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessagePageResponse {
    private List<ChatMessageResponse> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private long numberOfElements;
    private int totalPages;
    private boolean last;
}
