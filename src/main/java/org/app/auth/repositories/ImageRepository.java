package org.app.auth.repositories;

import org.app.auth.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    Image findFirstByFilenameAndUsername(String filename, String username);
    List<Image> findAllByUsername(String username);
}
