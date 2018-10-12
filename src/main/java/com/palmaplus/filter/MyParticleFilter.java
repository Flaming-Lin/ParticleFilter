package com.palmaplus.filter;

import com.palmaplus.entity.Particle;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @Author Flaming
 * @Contact xiaolin.tang@palmaplus.com
 * @date 2018/10/10 16:19
 */
public class MyParticleFilter implements ParticleFilter{

    private int particleNum;
    private List<Particle> particles;
    private int scale;
    private double weightThreshold;
    private double predictOffset;

    public MyParticleFilter(int particleNum, int scale, double weightThreshold){
        this.particleNum = particleNum;
        this.scale = scale;
        this.weightThreshold = weightThreshold;
    }

    @Override
    public List<Double> getResultValues(Double primaryValue, List<Double> observedValues){
        generateParticles(primaryValue);

        List<Double> resultValues = new ArrayList<>(observedValues.size());
        Double preValue = null;

        for(Double observedValue : observedValues){
            updateWeight(observedValue);
            reSample();
            preValue = predict(preValue == null ? primaryValue : preValue, observedValue);
            resultValues.add(preValue);
        }

        return resultValues;
    }

    private void generateParticles(double primaryValue){
        this.particles = (particleNum <= 0) ? new ArrayList<>(0) : new ArrayList<>(particleNum);

        Random random = new Random();
        for(int i = 0; i < particleNum; i++){
            int reverse = random.nextBoolean() ? 1 : -1;
            Particle particle = new Particle();
            particle.setWeight(1 / (double)particleNum);
            particle.setValue(primaryValue + random.nextInt(scale) * reverse + random.nextGaussian());
            particles.add(particle);
        }
    }

    private Double predict(double preValue, double curValue){
        double result = 0;
        for(Particle particle : particles){
            result += particle.getWeight() * particle.getValue();
        }

        double offset = curValue - preValue;
        particles.forEach(p -> p.setValue(p.getValue() + offset));

        return result;
    }

    private void reSample(){
        try {
            List<Particle> newParticles = particles.stream().sorted(Comparator.comparing(Particle::getWeight)).collect(Collectors.toList());
            Collections.reverse(newParticles);
            int reSampleNum = (int)(weightThreshold * particles.size());
            particles = new ArrayList<>();
            for(int i = 0; i < reSampleNum; i++){
                particles.add((Particle)newParticles.get(i).clone());
            }
            for(int i = 0; i < newParticles.size() - reSampleNum; i++){
                particles.add((Particle)newParticles.get(i).clone());
            }

            double sumWeight = particles.stream().map(Particle::getWeight).reduce(new BinaryOperator<Double>() {
                @Override
                public Double apply(Double aDouble, Double aDouble2) {
                    return aDouble + aDouble2;
                }
            }).orElse(-1D);
            particles.forEach(p -> p.setWeight(p.getWeight() / sumWeight));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private void updateWeight(double curValue){
        particles.forEach(p -> p.setWeight(1 / Math.abs(curValue - p.getValue())));
    }

    private void checkSumWeight(){
        double sumWeight = particles.stream().map(Particle::getWeight).reduce(new BinaryOperator<Double>() {
            @Override
            public Double apply(Double aDouble, Double aDouble2) {
                return aDouble + aDouble2;
            }
        }).orElse(-1D);
        System.out.println(sumWeight);
    }
}
