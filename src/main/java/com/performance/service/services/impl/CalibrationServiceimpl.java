package com.performance.service.services.impl;

import com.performance.service.dto.*;
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
                .objective(ReponseData.builder()
                        .total(cant)
                        .content(calibrationRepo.getCalificationList(vidssff,idssff,
                                request.getLstCalification(),request.getIdProcess(),vsize,request.getPage()*vsize))
                        .build())
                .status(true)
                .message("ok")

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
