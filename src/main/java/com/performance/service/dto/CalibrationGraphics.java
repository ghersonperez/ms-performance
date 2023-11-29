package com.performance.service.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalibrationGraphics {

    private List<CalificationResume> resume;
    private List<CalificationTable> table;
}
