package eu.zderadicka.spoiler;

import java.util.Random;

import static java.lang.Math.log;

class ExpRandom {
    private Random rng = new Random();
    private double lambda;
    ExpRandom(double lambda) {
        this.lambda = lambda;
    }

    double next() {
        return -log(rng.nextDouble())/lambda;
    }

    

}
