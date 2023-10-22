package com.evigel.sunnytable.controller;

import com.evigel.sunnytable.dto.DataSetDto;
import com.evigel.sunnytable.dto.UncertainResult;
import com.evigel.sunnytable.service.IExpressionService;
import com.evigel.sunnytable.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expression")
public class ExpressionController {

    @Autowired
    private IExpressionService expressionService;

    @RequestMapping("/average")
    public ResponseEntity<List<Double>> getAverage(@RequestBody double[][] list) {
        List<Double> list2 = expressionService.getAverage(list);
        return ResponseEntity.ok().body(list2);
    }

    @RequestMapping("/sum")
    public ResponseEntity<List<Double>> getSum(@RequestBody double[][] list) {
        List<Double> list2 = expressionService.getSum(list);
        return ResponseEntity.ok().body(list2);

    }

    @RequestMapping("/variance")
    public ResponseEntity<List<Double>> getVariance(@RequestBody double[][] list) {
        List<Double> list2 = expressionService.getVariance(list);
        return ResponseEntity.ok().body(list2);

    }

    @RequestMapping("/relativeerror")
    public ResponseEntity<List<Double>> getRelativeError(@RequestBody double[][] list) {
        List<Double> list2 = expressionService.getRelativeError(list);
        return ResponseEntity.ok().body(list2);

    }

    @RequestMapping("/uncertainty")
    public ResponseEntity<List<UncertainResult>> getUncertainty(@RequestBody double[][] list) {
        List<UncertainResult> list2 = expressionService.getUncertainty(list);
        return ResponseEntity.ok().body(list2);

    }

}
