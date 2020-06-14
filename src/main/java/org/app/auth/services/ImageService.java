package org.app.auth.services;

import org.app.auth.entities.Image;

import java.util.List;

public interface ImageService {
    void saveImage(Image image);
    String getPrediction(String filename, String username);
    void delImage(String filename, String username);
    List<Image> getImagesByUsername(String username);
}
