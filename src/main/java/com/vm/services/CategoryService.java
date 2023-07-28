package com.vm.services;

import com.vm.dto.CategoryRequest;
import com.vm.entities.Category;
import com.vm.exceptions.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {
    Category save(CategoryRequest categoryRequest);
    Category edit(Long id, CategoryRequest categoryRequest) throws CategoryNotFoundException;
    Category findCategoryById(Long id) throws CategoryNotFoundException;
    List<Category> getAllCategory();
    void deleteCategoryById(Long id) throws CategoryNotFoundException;
}
