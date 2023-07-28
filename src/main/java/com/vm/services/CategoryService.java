package com.vm.services;

import com.vm.dto.CategoryRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.Category;
import com.vm.exceptions.CustomNotFoundException;

import java.util.List;

public interface CategoryService {
    ResultDto save(CategoryRequest categoryRequest);
    ResultDto edit(Long id, CategoryRequest categoryRequest) throws CustomNotFoundException;
    Category findCategoryById(Long id) throws CustomNotFoundException;
    List<Category> getAllCategory();
    ResultDto deleteCategoryById(Long id) throws CustomNotFoundException;
}
