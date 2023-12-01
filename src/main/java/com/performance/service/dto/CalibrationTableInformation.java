package com.performance.service.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalibrationTableInformation {
    private Integer total;
    private Boolean status;
    private String message;
    private List<CalibrationProjection> content;


}
