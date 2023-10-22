package com.evigel.sunnytable.service;

import com.evigel.sunnytable.dto.UncertainResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public interface IExpressionService {
    List<Double> getAverage(double[][] list);
    List<Double> getSum(double[][] list);
    List<Double> getVariance(double[][] list);
    List<Double> getRelativeError(double[][] list);
    List<UncertainResult> getUncertainty(double[][] list);
}
