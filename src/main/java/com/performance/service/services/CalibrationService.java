package com.performance.service.services;

import com.performance.service.dto.CalibrationGraphics;
import com.performance.service.dto.CalibrationProjection;
import com.performance.service.dto.CalibrationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface CalibrationService {
    List<CalibrationProjection> getCalification( CalibrationRequest request);
    CalibrationGraphics getCalificationGraphics( CalibrationRequest request);

}
