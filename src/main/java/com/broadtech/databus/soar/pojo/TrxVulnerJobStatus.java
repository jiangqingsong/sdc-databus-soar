package com.broadtech.databus.soar.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: leo.j
 * @desc:
 * @Date: 2022/4/28 11:12 上午
 */
@Data
public class TrxVulnerJobStatus {
    private Boolean success;
    private Integer status;
    private String progress;

    public TrxVulnerJobStatus() {
    }

    public TrxVulnerJobStatus(Boolean success, Integer status, String progress) {
        this.success = success;
        this.status = status;
        this.progress = progress;
    }
}
