package com.performance.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public interface CalificationTable {

    public Integer getAlta();
    public Integer getBaja();
    public Integer getEsperada();
    public Integer getExcepcional();
    public Integer getMejorable();
    public String getName();
}
