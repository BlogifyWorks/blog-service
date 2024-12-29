package com.tayyub.blogservice.util;

import com.tayyub.blogservice.dto.BlogDTO;
import com.tayyub.blogservice.dto.CategoryDTO;
import com.tayyub.blogservice.dto.ImageDTO;
import com.tayyub.blogservice.entity.Blog;
import com.tayyub.blogservice.entity.Category;
import com.tayyub.blogservice.entity.Image;

import java.util.stream.Collectors;

public class DTOMapper {
 public static  ImageDTO toImageDTO(Image image){
     ImageDTO dto = new ImageDTO();
     dto.setId(image.getId());
     dto.setBlogId(image.getBlog().getId());
     dto.setImageUrl(image.getImageUrl());
     dto.setDescription(image.getDescription());
     dto.setCreatedAt(image.getCreatedAt());
     return dto;
 }
    public static CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    public static BlogDTO toBlogDTO(Blog blog) {
     BlogDTO dto = new BlogDTO();
     dto.setId(blog.getId());
     dto.setTitle(blog.getTitle());
     dto.setContent(blog.getContent());
     dto.setAuthorId(blog.getAuthorId());
     dto.setCreatedAt(blog.getCreatedAt());
     dto.setUpdatedAt(blog.getUpdatedAt());
     if(blog.getCategory() != null){
         dto.setCategory(toCategoryDTO(blog.getCategory()));
     }
     if(blog.getImages() != null){
         dto.setImages(blog.getImages().stream().map(DTOMapper::toImageDTO).collect(Collectors.toList()));
     }
     return dto;
    }



}
