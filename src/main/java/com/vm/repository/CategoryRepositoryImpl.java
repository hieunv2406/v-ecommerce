package com.vm.repository;

import com.vm.dto.Datatable;
import com.vm.dto.PageDto;
import com.vm.dto.category.CategoryDto;
import com.vm.dto.category.CategorySearchRequest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CategoryRepositoryImpl extends BaseRepository implements CategoryCustomRepository {
    @Override
    public Datatable getCategoriesPage(CategorySearchRequest categorySearchRequest) {
        PageDto pageDTO = sqlSearch(categorySearchRequest);
        return getListDataTableBySqlQuery(pageDTO.getSqlQuery(),
                pageDTO.getParameters(), categorySearchRequest.getPage(), categorySearchRequest.getPageSize(),
                CategoryDto.class,
                categorySearchRequest.getSortName(), categorySearchRequest.getSortType());
    }

    private PageDto sqlSearch(CategorySearchRequest categorySearchRequest) {
        PageDto pageDTO = new PageDto();
        Map<String, Object> parameter = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT\n" +
                "c.category_id categoryId,\n" +
                "c.category_name categoryName,\n" +
                "c.description description,\n" +
                "c.created_by createdBy,\n" +
                "c.created_at createdAt,\n" +
                "c.updated_by updatedBy,\n" +
                "c.updated_at updateAt\n" +
                "FROM category c\n" +
                "WHERE 1 = 1 ");
        if (categorySearchRequest != null) {
            if (categorySearchRequest.getCategoryName() != null) {
                sql.append(" And c.category_name = :category_name ");
                parameter.put("category_name", categorySearchRequest.getCategoryName());
            }
            if (categorySearchRequest.getDescription() != null) {
                sql.append(" And c.description = :description ");
                parameter.put("description", categorySearchRequest.getDescription());
            }
            if (categorySearchRequest.getCreatedAt() != null) {
                sql.append(" And c.created_at = :created_at ");
                parameter.put("created_at", categorySearchRequest.getCreatedAt());
            }
        }
        sql.append(" ORDER BY c.created_at DESC ");
        pageDTO.setSqlQuery(sql.toString());
        pageDTO.setParameters(parameter);
        return pageDTO;
    }
}
