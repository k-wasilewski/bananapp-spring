package org.app.auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.*;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {
    @Autowired
    ImageService imageService;
    @Autowired
    ImageRepository imageRepository;
    private Image image;

    @Before
    public void init() {
        image = new Image();
        image.setFilename("test.jpeg");
        image.setUsername("user1");
        image.setScore("5");
        image.setAcc("0.75");
    }

    @Test
    @Transactional
    public void saveImage() {
        imageService.saveImage(image);

        assertEquals(image.getFilename(),
                imageRepository.findFirstByFilenameAndUsername("test.jpeg", "user1")
                .getFilename());
    }

    @Test
    public void getPrediction() {
        imageRepository.save(image);
        String prediction = "score:"+image.getScore()+",acc:"+image.getAcc();

        assertEquals(prediction, imageService.getPrediction("test.jpeg", "user1"));

    }

    @Test
    public void delImage() {
        imageRepository.save(image);
        int size = imageRepository.findAll().size();
        imageService.delImage("test.jpeg", "user1");
        assertEquals(size-1, imageRepository.findAll().size());
    }

    @Test
    public void getImagesByUsername() {
        imageRepository.save(image);
        assertEquals(image.getUsername(), imageService
                .getImagesByUsername("user1")
        .get(0)
        .getUsername());
        assertEquals(image.getFilename(), imageService
                .getImagesByUsername("user1")
                .get(0)
                .getFilename());
    }
}
