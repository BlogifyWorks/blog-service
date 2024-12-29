package com.tayyub.blogservice.service;

import com.tayyub.blogservice.dto.BlogDTO;
import com.tayyub.blogservice.dto.CategoryDTO;
import com.tayyub.blogservice.dto.ImageDTO;
import com.tayyub.blogservice.entity.Blog;
import com.tayyub.blogservice.entity.Category;
import com.tayyub.blogservice.entity.Image;
import com.tayyub.blogservice.repository.BlogRepository;
import com.tayyub.blogservice.repository.CategoryRepository;
import com.tayyub.blogservice.repository.ImageRepository;
import com.tayyub.blogservice.util.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;

    public List<BlogDTO>getAllBlogs(){
        List<Blog> blogs = blogRepository.findAll();
        return blogs.stream().map(DTOMapper::toBlogDTO).toList();
    }

    public BlogDTO getBlogById(Long id){
        Blog blog = blogRepository.findById(id).orElseThrow(()->new RuntimeException("Blog not found"));
        return DTOMapper.toBlogDTO(blog);
    }
    public BlogDTO createBlog(Blog blog){
        if (blog.getCategory() != null && blog.getCategory().getId() != null) {
            Category category = categoryRepository.findById(blog.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            blog.setCategory(category);
        }

        Blog savedBlog= blogRepository.save(blog);
        return DTOMapper.toBlogDTO(savedBlog);

    }

    public BlogDTO assignCategoryToBlog(Long blogId, Long categoryId){
        Blog blog = blogRepository.findById(blogId).orElseThrow(()->new RuntimeException("Blog not found"));
        Category category= categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Category not found"));
        blog.setCategory(category);
        Blog updateBlog= blogRepository.save(blog);
        return DTOMapper.toBlogDTO(updateBlog);
    }

    public ImageDTO addImageToBlog(Long blogId, Image image){
        Blog blog = blogRepository.findById(blogId).orElseThrow(()->new RuntimeException("Blog not found"));
        image.setBlog(blog);
        Image savedImage= imageRepository.save(image);
        return DTOMapper.toImageDTO(savedImage);
    }
    public List<ImageDTO> getImagesForBlog(Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        return blog.getImages().stream().map(DTOMapper::toImageDTO).toList();
    }
    public CategoryDTO getCategoryForBlog(Long blogId) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));
        return DTOMapper.toCategoryDTO(blog.getCategory());
    }

    public CategoryDTO createCategory(Category category) {
        Category savedCategory = categoryRepository.save(category);
        return DTOMapper.toCategoryDTO(savedCategory);
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(DTOMapper::toCategoryDTO).toList();
    }

    public List<ImageDTO> getAllImages() {
        List<Image> images = imageRepository.findAll();
        return images.stream().map(DTOMapper::toImageDTO).toList();
    }
}
