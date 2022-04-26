package com.broadtech.databus.soar.pojo;

import lombok.Data;

import java.util.List;

@Data
public class SoarCapacityLabelResult {
    private String value;
    private String label;
    private List<SoarCapacityLabelResult> children;
}
