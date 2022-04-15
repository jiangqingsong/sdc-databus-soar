package com.broadtech.databus.soar.service;

import lombok.Data;

import java.util.List;

@Data
public class SoarEventTypeResult {
    private String value;
    private String label;
    private List<SoarEventTypeResult> children;
}
