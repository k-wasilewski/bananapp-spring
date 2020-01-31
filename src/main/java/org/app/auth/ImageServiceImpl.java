package org.app.auth;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
    public String getPrediction(String filename) {
        Image image = imageRepository.findFirstByFilename(filename);
        return "score:"+image.getScore()+",acc:"+image.getAcc();
    }
}
