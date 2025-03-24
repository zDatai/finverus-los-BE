package com.zdatai.finverus.request.permission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRolMapRequest {
    private long userId;
    private List<Long> roleIds;
    private Date fromDate;
    private Date toDate;
    private int active;
}
