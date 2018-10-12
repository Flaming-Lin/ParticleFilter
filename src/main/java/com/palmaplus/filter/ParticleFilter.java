package com.palmaplus.filter;

import java.util.List;

/**
 * @Author Flaming
 * @Contact xiaolin.tang@palmaplus.com
 * @date 2018/10/11 15:31
 */
public interface ParticleFilter {

    List<Double> getResultValues(Double primaryValue, List<Double> observedValues);

}
