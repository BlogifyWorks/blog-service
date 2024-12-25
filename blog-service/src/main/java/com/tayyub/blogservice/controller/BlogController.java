package com.tayyub.blogservice.controller;


import com.tayyub.blogservice.dto.ImageDTO;
import com.tayyub.blogservice.entity.Blog;
import com.tayyub.blogservice.entity.Category;
import com.tayyub.blogservice.entity.Image;
import com.tayyub.blogservice.repository.BlogRepository;
import com.tayyub.blogservice.repository.CategoryRepository;
import com.tayyub.blogservice.repository.ImageRepository;
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
    private BlogRepository blogRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @GetMapping
    public List<Blog> getAllBlogs(){
        return blogRepository.findAll();
    }


    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog){

        if (blog.getCategory() != null && blog.getCategory().getId() != null) {
            Category category = categoryRepository.findById(blog.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            blog.setCategory(category);
        }

        Blog savedBlog= blogRepository.save(blog);
        return ResponseEntity.ok().body(savedBlog);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id){
        return blogRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{blogId}/images")
    public ResponseEntity<Image> addImageToBlog(@PathVariable Long blogId, @RequestBody Image image){

        Blog blog = blogRepository.findById(blogId).orElseThrow(()->new RuntimeException("Blog not found"));
        image.setBlog(blog);
        Image savedImage= imageRepository.save(image);
        return ResponseEntity.ok().body(savedImage);
    }

    @PutMapping("/{blogId}/category")
    public ResponseEntity<Blog>assignCategoryToBlog(@PathVariable Long blogId, @RequestBody Long categoryId){
       Blog blog = blogRepository.findById(blogId).orElseThrow(()->new RuntimeException("Blog not found"));
        Category category= categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category not found"));
        blog.setCategory(category);
        Blog updateBlog= blogRepository.save(blog);
        return ResponseEntity.ok().body(updateBlog);
    }

    @GetMapping("/{blogId}/images")
    public ResponseEntity<List<Image>> getImagesForBlog(@PathVariable Long blogId){
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        return ResponseEntity.ok(blog.getImages());
    }

    @GetMapping("/{blogId}/category")
    public ResponseEntity<Category> getCategoryForBlog(@PathVariable Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        return ResponseEntity.ok(blog.getCategory());
    }
    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryRepository.save(category);
        return ResponseEntity.ok(savedCategory);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("images")
    public ResponseEntity<List<ImageDTO>>getAllImages() {
        List<Image> images = imageRepository.findAll();
        List<ImageDTO> imageDTOS = images.stream().map(DTOMapper::toImageDTO).toList();
        return ResponseEntity.ok(imageDTOS);
    }


}
