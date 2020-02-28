package org.app.auth;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private ImageRepository imageRepository;

    public ImageServiceImpl(@Lazy ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void saveImage(Image image) {
        imageRepository.save(image);
    }
    @Override
    public String getPrediction(String filename, String username) {
        Image image = imageRepository.findFirstByFilenameAndUsername(filename, username);
        return "score:"+image.getScore()+",acc:"+image.getAcc();
    }
    @Override
    public void delImage(String filename, String username) {
        Image image = imageRepository.findFirstByFilenameAndUsername(filename, username);
        imageRepository.delete(image);
    }

    @Override
    public List<Image> getImagesByUsername(String username) {
        return imageRepository.findAllByUsername(username);
    }
}
