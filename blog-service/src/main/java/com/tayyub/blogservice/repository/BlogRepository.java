package com.tayyub.blogservice.repository;

import com.tayyub.blogservice.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {

}
