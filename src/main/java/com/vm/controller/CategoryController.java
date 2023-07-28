package com.vm.controller;

import com.vm.dto.CategoryRequest;
import com.vm.entities.Category;
import com.vm.exceptions.CategoryNotFoundException;
import com.vm.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryService.getAllCategory();
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }
    @PostMapping(value = "/add")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.save(categoryRequest);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> eiditCategoryById(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest)  throws CategoryNotFoundException {
        Category category = categoryService.edit(id, categoryRequest);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable Long id) throws CategoryNotFoundException {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
