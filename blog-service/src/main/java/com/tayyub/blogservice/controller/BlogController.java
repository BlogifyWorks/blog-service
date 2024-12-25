package com.tayyub.blogservice.controller;


import com.tayyub.blogservice.entity.Blog;
import com.tayyub.blogservice.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;


    @GetMapping
    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){
        Blog savedBlog= blogRepository.save(blog);
        return ResponseEntity.ok().body(savedBlog);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id){
        return blogRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


}
