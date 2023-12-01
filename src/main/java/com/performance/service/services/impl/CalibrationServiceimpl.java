package com.performance.service.services.impl;

import com.performance.service.dto.CalibrationGraphics;
import com.performance.service.dto.CalibrationProjection;
import com.performance.service.dto.CalibrationRequest;
import com.performance.service.dto.CalibrationTableInformation;
import com.performance.service.repository.IEvaluatedRepository;
import com.performance.service.services.CalibrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalibrationServiceimpl implements CalibrationService {

    @Autowired
    IEvaluatedRepository calibrationRepo;

    @Override
    public CalibrationTableInformation getCalification(CalibrationRequest request) {
        String idssff = String.join(",", request.getIdssffEvaluated());

        String vidssff = "%" + request.getIdssff()+ "%";
        int cant = calibrationRepo.getCalificationCount(vidssff,idssff,
                request.getLstCalification(),request.getIdProcess());
    int vsize=10;
        return CalibrationTableInformation.builder()
                .total(cant)
                .status(true)
                .message("ok")
                .content(calibrationRepo.getCalificationList(vidssff,idssff,
                        request.getLstCalification(),request.getIdProcess(),vsize,request.getPage()*vsize))
                .build();
    }

    @Override
    public CalibrationGraphics getCalificationGraphics(CalibrationRequest request) {
        String idssff = String.join(",", request.getIdssffEvaluated());

        return CalibrationGraphics.builder()
                .table(calibrationRepo.getListCalibrationTable(idssff,request.getIdProcess(),request.getTotal()))
                .resume(calibrationRepo.getCalificationResume(idssff,request.getIdProcess()))
                .build();
    }
}
