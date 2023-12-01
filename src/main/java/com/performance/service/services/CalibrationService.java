package com.performance.service.services;

import com.performance.service.dto.CalibrationGraphics;
import com.performance.service.dto.CalibrationProjection;
import com.performance.service.dto.CalibrationRequest;
import com.performance.service.dto.CalibrationTableInformation;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public interface CalibrationService {
    CalibrationTableInformation getCalification(CalibrationRequest request);
    CalibrationGraphics getCalificationGraphics( CalibrationRequest request);

}
