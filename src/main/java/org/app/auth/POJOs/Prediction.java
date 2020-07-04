package org.app.auth.POJOs;

public class Prediction {
    String filename;
    Double score;
    Double accuracy;

    public Prediction() {}

    public Prediction(Double score, Double accuracy) {
        this.score=score;
        this.accuracy=accuracy;
    }

    public Prediction(Double score, Double accuracy, String filename) {
        this.score=score;
        this.accuracy=accuracy;
        this.filename=filename;
    }

    public Double getScore() {
        return score;
    }

    public Double getAccuracy() {
        return accuracy;
    }
}
