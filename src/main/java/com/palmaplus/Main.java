package com.palmaplus;

import com.palmaplus.filter.CSharpParticleFilter;
import com.palmaplus.filter.MyParticleFilter;
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
        ParticleFilter myParticleFilter = new MyParticleFilter(1000, 50, 0.1);
        ParticleFilter cSharpParticleFilter = new CSharpParticleFilter(1000, 50, 0.1);
//        Double[] values = new Double[]{61D, 100D, 70D, 90D, 101D, 62D, 83D, 150D, 70D};
        Double[] values = new Double[]{61D, 70D, 68D, 75D, 89D, 86D, 75D, 60D, 60D, 60D, 60D, 60D, 60D};
        List<Double> observedValues = new ArrayList<>(Arrays.asList(values));
        System.out.println(myParticleFilter.getResultValues(60D, observedValues).toString());
        System.out.println(cSharpParticleFilter.getResultValues(60D, observedValues).toString());
    }

}
