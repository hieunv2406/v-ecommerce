package com.vm.services;

import com.vm.dto.Datatable;
import com.vm.dto.ResultDto;
import com.vm.dto.category.CategoryRequest;
import com.vm.dto.category.CategorySearchRequest;
import com.vm.entities.Category;
import com.vm.exceptions.CustomException;
import com.vm.repository.CategoryCustomRepository;
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
    private final CategoryCustomRepository categoryCustomRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryCustomRepository categoryCustomRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryCustomRepository = categoryCustomRepository;
    }

    private static final String CATEGORY_NOT_FOUND = "Category not found";

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
    public ResultDto edit(Long id, CategoryRequest categoryRequest) throws CustomException {
        log.info("Edit category by id: {}", id);
        Optional<Category> category = categoryRepository.findByCategoryId(id);
        if (!category.isPresent()) {
            log.error(CATEGORY_NOT_FOUND);
            throw new CustomException(CATEGORY_NOT_FOUND);
        }
        Category categoryModified = category.get();
        categoryModified.setCategoryName(categoryRequest.getCategoryName());
        categoryModified.setDescription(categoryRequest.getDescription());
        categoryRepository.save(categoryModified);
        return new ResultDto(Constants.ResponseKey.SUCCESS, "Category modified", categoryModified);
    }

    @Override
    public Category findCategoryById(Long id) throws CustomException {
        log.info("Find category by id: {}", id);
        Optional<Category> category = categoryRepository.findByCategoryId(id);
        if (!category.isPresent()) {
            log.error(CATEGORY_NOT_FOUND);
            throw new CustomException(CATEGORY_NOT_FOUND);
        }
        return category.get();
    }

    @Override
    public List<Category> getAllCategory() {
        log.info("Get all category");
        return categoryRepository.findAll();
    }

    @Override
    public ResultDto deleteCategoryById(Long id) throws CustomException {
        log.info("Delete category by id: {}", id);
        Optional<Category> category = categoryRepository.findByCategoryId(id);
        if (!category.isPresent()) {
            log.error(CATEGORY_NOT_FOUND);
            throw new CustomException(CATEGORY_NOT_FOUND);
        }
        categoryRepository.delete(category.get());
        return new ResultDto(Constants.ResponseKey.SUCCESS, "Category deleted");
    }

    @Override
    public Datatable search(CategorySearchRequest categorySearchRequest) {
        log.info("Search category");
        return categoryCustomRepository.getCategoriesPage(categorySearchRequest);
    }
}
