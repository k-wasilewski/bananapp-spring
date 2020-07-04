package org.app.auth.services;

import org.app.auth.POJOs.Prediction;
import org.app.auth.entities.Image;
import org.app.auth.repositories.ImageRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        //given
        image = new Image();
        image.setFilename("image_service.jpeg");
        image.setUsername("image_service_user");
        image.setScore(5.0);
        image.setAcc(0.75);
        imageService.saveImage(image);
    }

    @Test
    @Transactional
    public void saveImage() {
        //when
        Image savedImage = imageRepository.findFirstByFilenameAndUsername(
                "image_service.jpeg", "image_service_user");
        //then
        assertEquals(image.getFilename(), savedImage.getFilename());
    }

    @Test
    @Transactional
    public void getPrediction() {
        //when
        String prediction = "score:"+image.getScore()+",accuracy:"+image.getAcc();
        Prediction savedPrediction = imageService.getPrediction(
                "image_service.jpeg", "image_service_user");

        //then
        assertEquals(prediction, savedPrediction);

    }

    @Test
    @Transactional
    public void delImage() {
        //given
        int size = imageRepository.findAll().size();

        //when
        imageService.delImage("image_service.jpeg",
                "image_service_user");

        //then
        assertEquals(size-1, imageRepository.findAll().size());
    }

    @Test
    @Transactional
    public void getImagesByUsername() {
        //when
        List<Image> savedImages = imageService
                .getImagesByUsername("image_service_user");

        //then
        assertEquals(image.getUsername(), savedImages.get(0).getUsername());
        assertEquals(image.getFilename(), savedImages.get(0).getFilename());
    }
}
