package com.tayyub.blogservice.util;

import com.tayyub.blogservice.dto.CategoryDTO;
import com.tayyub.blogservice.dto.ImageDTO;
import com.tayyub.blogservice.entity.Category;
import com.tayyub.blogservice.entity.Image;

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



}
