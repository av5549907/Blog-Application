package com.blogging.blog.serviceImpl;

import com.blogging.blog.entities.Category;
import com.blogging.blog.exception.ResourceNotFoundException;
import com.blogging.blog.payloads.CategoryDto;
import com.blogging.blog.repository.CategoryRepo;
import com.blogging.blog.payloads.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public String start() {
        return "Welcome to Category Service";
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=dtoToCategory(categoryDto);
        categoryRepo.save(category);
        return CategoryToDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("","Category","Id",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        categoryRepo.save(category);
        return CategoryToDto(category);

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("","Category","Id",categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category=categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("","Catgeory","Id",categoryId));
        return CategoryToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories=categoryRepo.findAll();
        return categories.stream().map((category)->CategoryToDto(category)).collect(Collectors.toList());
    }

    public Category dtoToCategory(CategoryDto categoryDto){
        return this.modelMapper.map(categoryDto,Category.class);
    }

    public  CategoryDto CategoryToDto(Category category){
        return this.modelMapper.map(category,CategoryDto.class);
    }
}
