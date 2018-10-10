package com.palmaplus.entity;

import lombok.Data;

/**
 * @Author Flaming
 * @Contact xiaolin.tang@palmaplus.com
 * @date 2018/10/10 16:16
 */
@Data
public class Particle implements Comparable<Particle>{

    private double value;
    private double weight;

    @Override
    public int compareTo(Particle o) {
        double oValue = o.getValue();
        if(this.value == oValue){
            return 0;
        } else if(this.value > oValue){
            return 1;
        } else{
            return -1;
        }
    }

}
