package com.vm.services;

import com.vm.dto.Datatable;
import com.vm.dto.ResultDto;
import com.vm.dto.category.CategoryRequest;
import com.vm.dto.category.CategorySearchRequest;
import com.vm.entities.Category;
import com.vm.exceptions.CustomException;

import java.util.List;

public interface CategoryService {
    ResultDto save(CategoryRequest categoryRequest);

    ResultDto edit(Long id, CategoryRequest categoryRequest) throws CustomException;

    Category findCategoryById(Long id) throws CustomException;

    List<Category> getAllCategory();

    ResultDto deleteCategoryById(Long id) throws CustomException;

    Datatable search(CategorySearchRequest categorySearchRequest);
}
