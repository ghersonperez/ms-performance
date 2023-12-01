package com.performance.service.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReponseData {

    private List<CalibrationProjection> content;
    private Integer total;
}
