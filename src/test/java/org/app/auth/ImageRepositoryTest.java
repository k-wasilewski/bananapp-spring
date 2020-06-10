package org.app.auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        //given
        image = new Image();
        image.setFilename("image_repo_test.jpeg");
        image.setUsername("image_repo_user");
        imageRepository.save(image);
    }

    @Test
    public void findFirstByFilenameAndUsername() {
        //when
        Image savedImage = imageRepository.findFirstByFilenameAndUsername(
                        "image_repo_test.jpeg","image_repo_user");

        //then
        assertEquals(image.getFilename(), savedImage.getFilename());
        assertEquals(image.getUsername(),savedImage.getUsername());
    }

    @Test
    public void findAllByUsername() {
        //given
        List<Image> list = new ArrayList<>();
        list.add(image);

        //when
        List<Image> savedImages = imageRepository.findAllByUsername("image_repo_user");

        //then
        assertEquals(list.size(), savedImages.size());
        assertEquals(image.getFilename(), savedImages.get(0).getFilename());
        assertEquals(image.getUsername(), savedImages.get(0).getUsername());
    }
}
