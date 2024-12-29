package com.tayyub.blogservice.controller;


import com.tayyub.blogservice.dto.BlogDTO;
import com.tayyub.blogservice.dto.CategoryDTO;
import com.tayyub.blogservice.dto.ImageDTO;
import com.tayyub.blogservice.entity.Blog;
import com.tayyub.blogservice.entity.Category;
import com.tayyub.blogservice.entity.Image;
import com.tayyub.blogservice.repository.BlogRepository;
import com.tayyub.blogservice.repository.CategoryRepository;
import com.tayyub.blogservice.repository.ImageRepository;
import com.tayyub.blogservice.service.BlogService;
import com.tayyub.blogservice.util.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {



    @Autowired
    private BlogService blogService;


    @GetMapping
    public ResponseEntity<List<BlogDTO>> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }


    @PostMapping
    public ResponseEntity<BlogDTO> createBlog(@RequestBody Blog blog){

        return ResponseEntity.ok(blogService.createBlog(blog));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDTO> getBlogById(@PathVariable Long id){
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

    @PostMapping("/{blogId}/images")
    public ResponseEntity<ImageDTO> addImageToBlog(@PathVariable Long blogId, @RequestBody Image image){
        return ResponseEntity.ok(blogService.addImageToBlog(blogId, image));
    }

    @PutMapping("/{blogId}/category")
    public ResponseEntity<BlogDTO>assignCategoryToBlog(@PathVariable Long blogId, @RequestBody Long categoryId){
        return ResponseEntity.ok(blogService.assignCategoryToBlog(blogId, categoryId));
    }

    @GetMapping("/{blogId}/images")
    public ResponseEntity<List<ImageDTO>> getImagesForBlog(@PathVariable Long blogId){
        return ResponseEntity.ok(blogService.getImagesForBlog(blogId));
    }

    @GetMapping("/{blogId}/category")
    public ResponseEntity<CategoryDTO> getCategoryForBlog(@PathVariable Long blogId) {
        return ResponseEntity.ok(blogService.getCategoryForBlog(blogId));
    }
    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody Category category) {
        return ResponseEntity.ok(blogService.createCategory(category));
    }
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(blogService.getAllCategories());
    }

    @GetMapping("/images")
    public ResponseEntity<List<ImageDTO>>getAllImages() {
        return ResponseEntity.ok(blogService.getAllImages());
    }


}
