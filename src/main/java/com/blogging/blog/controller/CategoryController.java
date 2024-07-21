package com.blogging.blog.controller;

import com.blogging.blog.payloads.ApiResponse;
import com.blogging.blog.payloads.CategoryDto;
import com.blogging.blog.payloads.ResponseHandler;
import com.blogging.blog.payloads.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/start")
    public ResponseEntity<Object> start(){
        return ResponseHandler.responseBuilder("Project is starting", HttpStatus.OK,categoryService.start());
    }

    @PostMapping("/")
    ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/")
    ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,Integer categoryId){
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto,categoryId), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    ResponseEntity<CategoryDto> getCategoryById(@PathVariable("categoryId") Integer categoryId){
        return  new ResponseEntity<>(categoryService.getCategoryById(categoryId),HttpStatus.OK);
    }
    @GetMapping("/")
    ResponseEntity<List<CategoryDto>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(),HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId){
        return new ResponseEntity<>(new ApiResponse("Category is deleted Successfully",true),HttpStatus.ACCEPTED);
    }
}
