package com.performance.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CalibrationRequest {

    private String lstCalification;
    private List<String> idssffEvaluated;
    private Integer idProcess;
    private Integer total;
}
