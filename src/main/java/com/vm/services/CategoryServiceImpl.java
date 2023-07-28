package com.vm.services;

import com.vm.dto.CategoryRequest;
import com.vm.entities.Category;
import com.vm.exceptions.CategoryNotFoundException;
import com.vm.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setDescription(categoryRequest.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public Category edit(Long id, CategoryRequest categoryRequest) throws CategoryNotFoundException {
        Optional<Category> category = categoryRepository.findByCategoryId(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException("Category not found");
        }
        Category categoryModified = category.get();
        categoryModified.setCategoryName(categoryRequest.getCategoryName());
        categoryModified.setDescription(categoryRequest.getDescription());
        return categoryRepository.save(categoryModified);
    }

    @Override
    public Category findCategoryById(Long id) throws CategoryNotFoundException {
        Optional<Category> category = categoryRepository.findByCategoryId(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException("Category not found");
        }
        return category.get();
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryById(Long id) throws CategoryNotFoundException {
        Optional<Category> category = categoryRepository.findByCategoryId(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException("Category not found");
        }
        categoryRepository.delete(category.get());
    }
}
