package org.app.auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageRepositoryTest {
    @Autowired
    ImageRepository imageRepository;
    private Image image;

    @Before
    public void init() {
        image = new Image();
        image.setFilename("test.jpeg");
        image.setUsername("user1");
        imageRepository.save(image);
    }

    @Test
    public void findFirstByFilenameAndUsername() {
        assertEquals(image.getFilename(),
                imageRepository
                        .findFirstByFilenameAndUsername("test.jpeg", "user1")
                        .getFilename());
        assertEquals(image.getUsername(),
                imageRepository
                        .findFirstByFilenameAndUsername("test.jpeg", "user1")
                        .getUsername());
    }

    @Test
    public void findAllByUsername() {
        List<Image> list = new ArrayList<>();
        list.add(image);

        assertEquals(list.size(),
                imageRepository.findAllByUsername("user1").size());
        assertEquals(image.getFilename(),
                imageRepository
                        .findAllByUsername("user1")
                        .get(0)
                        .getFilename());
        assertEquals(image.getUsername(),
                imageRepository
                        .findAllByUsername("user1")
                        .get(0)
                        .getUsername());
    }
}
