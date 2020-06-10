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
        image.setFilename("image_repo_test.jpeg");
        image.setUsername("image_repo_user");
        imageRepository.save(image);
    }

    @Test
    public void findFirstByFilenameAndUsername() {
        assertEquals(image.getFilename(),
                imageRepository
                        .findFirstByFilenameAndUsername(
                                "image_repo_test.jpeg",
                                "image_repo_user")
                        .getFilename());
        assertEquals(image.getUsername(),
                imageRepository
                        .findFirstByFilenameAndUsername(
                                "image_repo_test.jpeg",
                                "image_repo_user")
                        .getUsername());
    }

    @Test
    public void findAllByUsername() {
        List<Image> list = new ArrayList<>();
        list.add(image);

        assertEquals(list.size(),
                imageRepository.findAllByUsername("image_repo_user").size());
        assertEquals(image.getFilename(),
                imageRepository
                        .findAllByUsername("image_repo_user")
                        .get(0)
                        .getFilename());
        assertEquals(image.getUsername(),
                imageRepository
                        .findAllByUsername("image_repo_user")
                        .get(0)
                        .getUsername());
    }
}
