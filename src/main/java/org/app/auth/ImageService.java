package org.app.auth;

public interface ImageService {
    void saveImage(Image image);
    String getPrediction(String filename, String username);
    void delImage(String filename, String username);
}
