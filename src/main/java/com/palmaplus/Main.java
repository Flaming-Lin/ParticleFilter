package com.palmaplus;

import com.palmaplus.filter.ParticleFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Flaming
 * @Contact xiaolin.tang@palmaplus.com
 * @date 2018/10/10 17:38
 */
public class Main {

    public static void main(String[] args){
        ParticleFilter filter = new ParticleFilter(1000, 50, 0.1);
        Double[] values = new Double[]{61D, 100D, 70D, 90D, 101D, 62D, 83D, 150D, 70D};
        List<Double> observedValues = new ArrayList<>(Arrays.asList(values));
        List<Double> result = filter.getResultValues(61D, observedValues);
        System.out.println(result.toString());
    }

}
