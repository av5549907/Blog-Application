package com.blogging.blog.payloads.services;

import com.blogging.blog.entities.Category;
import com.blogging.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    String start();
    //create
    CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto category,Integer categoryId);
    //delete
    void deleteCategory(Integer categoryId);
    //get
    CategoryDto getCategoryById(Integer categoryId);
    //getAll

    List<CategoryDto> getAllCategories();

}
