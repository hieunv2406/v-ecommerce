package com.vm.repository;

import com.vm.dto.Datatable;
import com.vm.dto.category.CategorySearchRequest;

public interface CategoryCustomRepository {
    Datatable getCategoriesPage(CategorySearchRequest categorySearchRequest);
}
