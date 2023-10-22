package com.evigel.sunnytable.service.impl;

import com.evigel.sunnytable.dto.UncertainResult;
import com.evigel.sunnytable.service.IExpressionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExpressionServiceImpl implements IExpressionService {

    @Override
    public List<Double> getAverage(double[][] list) {
        int size = list.length;
        List<Double> list2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            double sum = 0;
            for (int j = 0; j < list[i].length; j++) {
                sum += list[i][j];
            }
            list2.add(sum / list[i].length);
        }
        return list2;
    }

    @Override
    public List<Double> getSum(double[][] list) {
        int size = list.length;
        List<Double> list2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            double sum = 0;
            for (int j = 0; j < list[i].length; j++) {
                sum += list[i][j];
            }
            list2.add(sum);
        }
        return list2;
    }

    @Override
    public List<Double> getVariance(double[][] list) {
        int size = list.length;
        List<Double> list2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            double sum = 0;
            for (int j = 0; j < list[i].length; j++) {
                sum += list[i][j];
            }
            double average = sum / list[i].length;
            double var = 0;
            for (int j = 0; j < list[i].length; j++) {
                var += (list[i][j] - average) * (list[i][j] - average);
            }
            list2.add(var / list[i].length);
        }
        return list2;
    }

    @Override
    public List<Double> getRelativeError(double[][] list) {
        int size = list.length;
        List<Double> list2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            double sum = 0;
            for (int j = 1; j < list[i].length; j++) {
                sum += list[i][j];
            }
            double average = sum / (list[i].length-1);
            list2.add(Math.abs((average - list[i][0]) / list[i][0]));
        }
        return list2;
    }

    @Override
    public List<UncertainResult> getUncertainty(double[][] list) {
        int size = list.length;
        List<UncertainResult> list2 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            double sum = 0;
            for (int j = 1; j < list[i].length; j++) {
                sum += list[i][j];
            }
            double average = sum / (list[i].length - 1);
            double var = 0;
            for (int j = 1; j < list[i].length; j++) {
                var += (list[i][j] - average) * (list[i][j] - average);
            }
            double ua = Math.sqrt(var / ((list[i].length - 1) * (list[i].length - 2)));
            double ub = list[i][0] / Math.sqrt(3);
            double u = Math.sqrt(ua * ua + ub * ub);
            UncertainResult result = new UncertainResult(ua, ub, u);
            list2.add(result);
        }
        return list2;
    }
}
