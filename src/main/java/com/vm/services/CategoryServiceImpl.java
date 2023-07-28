package com.vm.services;

import com.vm.dto.CategoryRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.Category;
import com.vm.exceptions.CustomNotFoundException;
import com.vm.repository.CategoryRepository;
import com.vm.utils.Constants;
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
    public ResultDto save(CategoryRequest categoryRequest) {
        log.info("Add new category");
        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setDescription(categoryRequest.getDescription());
        Category categoryInserted = categoryRepository.save(category);
        return new ResultDto(Constants.ResponseKey.SUCCESS, "Category created", categoryInserted);
    }

    @Override
    public ResultDto edit(Long id, CategoryRequest categoryRequest) throws CustomNotFoundException {
        log.info("Edit category by id: {}", id);
        Optional<Category> category = categoryRepository.findByCategoryId(id);
        if (!category.isPresent()) {
            log.error("Category not found");
            throw new CustomNotFoundException("Category not found");
        }
        Category categoryModified = category.get();
        categoryModified.setCategoryName(categoryRequest.getCategoryName());
        categoryModified.setDescription(categoryRequest.getDescription());
        categoryRepository.save(categoryModified);
        return new ResultDto(Constants.ResponseKey.SUCCESS, "Category modified", categoryModified);
    }

    @Override
    public Category findCategoryById(Long id) throws CustomNotFoundException {
        log.info("Find category by id: {}", id);
        Optional<Category> category = categoryRepository.findByCategoryId(id);
        if (!category.isPresent()) {
            log.error("Category not found");
            throw new CustomNotFoundException("Category not found");
        }
        return category.get();
    }

    @Override
    public List<Category> getAllCategory() {
        log.info("Get all category");
        return categoryRepository.findAll();
    }

    @Override
    public ResultDto deleteCategoryById(Long id) throws CustomNotFoundException {
        log.info("Delete category by id: {}", id);
        Optional<Category> category = categoryRepository.findByCategoryId(id);
        if (!category.isPresent()) {
            log.error("Category not found");
            throw new CustomNotFoundException("Category not found");
        }
        categoryRepository.delete(category.get());
        return new ResultDto(Constants.ResponseKey.SUCCESS, "Category deleted");
    }
}
