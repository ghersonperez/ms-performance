package com.performance.service.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalibrationTableInformation {

    private Boolean status;
    private String message;
    private ReponseData objective;


}
