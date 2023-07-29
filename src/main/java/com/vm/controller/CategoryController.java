package com.vm.controller;

import com.vm.dto.Datatable;
import com.vm.dto.ResultDto;
import com.vm.dto.category.CategoryRequest;
import com.vm.dto.category.CategorySearchRequest;
import com.vm.entities.Category;
import com.vm.exceptions.CustomNotFoundException;
import com.vm.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<ResultDto> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        ResultDto resultDto = categoryService.save(categoryRequest);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ResultDto> editCategoryById(@Valid @PathVariable Long id, @RequestBody CategoryRequest categoryRequest) throws CustomNotFoundException {
        ResultDto resultDto = categoryService.edit(id, categoryRequest);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) throws CustomNotFoundException {
        Category category = categoryService.findCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ResultDto> deleteCategoryById(@PathVariable Long id) throws CustomNotFoundException {
        ResultDto resultDto = categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PostMapping(value = "/search")
    public ResponseEntity<Datatable> search(@RequestBody CategorySearchRequest categorySearchRequest) {
        Datatable datatable = categoryService.search(categorySearchRequest);
        return new ResponseEntity<>(datatable, HttpStatus.OK);
    }
}
