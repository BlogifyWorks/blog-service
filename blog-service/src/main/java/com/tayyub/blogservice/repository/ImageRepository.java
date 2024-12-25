package com.tayyub.blogservice.repository;

import com.tayyub.blogservice.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
