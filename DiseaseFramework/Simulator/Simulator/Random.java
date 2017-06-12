package Simulator;

class Random {
    static double exponential(double mean) {
        return - mean * Math.log(Math.random());
    }
  static boolean bernoulli(double p) {
    return Math.random() < p;
  }
    /* .. and other distributions */
}