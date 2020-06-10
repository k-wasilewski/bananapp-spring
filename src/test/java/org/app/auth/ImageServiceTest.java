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
        image.setFilename("imgServTest.jpeg");
        image.setUsername("imgServUser");
        image.setScore("5");
        image.setAcc("0.75");
        imageService.saveImage(image);
    }

    @Test
    @Transactional
    public void saveImage() {
        assertEquals(image.getFilename(),
                imageRepository.findFirstByFilenameAndUsername("imgServTest.jpeg", "imgServUser")
                .getFilename());
    }

    @Test
    @Transactional
    public void getPrediction() {
        String prediction = "score:"+image.getScore()+",acc:"+image.getAcc();

        assertEquals(prediction, imageService.getPrediction("imgServTest.jpeg", "imgServUser"));

    }

    @Test
    @Transactional
    public void delImage() {
        int size = imageRepository.findAll().size();
        imageService.delImage("imgServTest.jpeg", "imgServUser");
        assertEquals(size-1, imageRepository.findAll().size());
    }

    @Test
    @Transactional
    public void getImagesByUsername() {
        assertEquals(image.getUsername(), imageService
                .getImagesByUsername("imgServUser")
        .get(0)
        .getUsername());
        assertEquals(image.getFilename(), imageService
                .getImagesByUsername("imgServUser")
                .get(0)
                .getFilename());
    }
}
