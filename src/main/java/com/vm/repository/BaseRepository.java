package com.vm.repository;

import com.vm.dto.Datatable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public abstract class BaseRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return namedParameterJdbcTemplate;
    }

    public Datatable getListDataTableBySqlQuery(String sqlQuery,
                                                Map<String, Object> parameters,
                                                int page,
                                                int pageSize,
                                                Class<?> mappedClass,
                                                String sortName,
                                                String sortType) {
        Datatable dataReturn = new Datatable();
        StringBuilder sqlQueryResult = new StringBuilder(" SELECT * FROM ( SELECT * FROM ( SELECT * FROM ( " + sqlQuery + " ) data ");
        if (sortName != null) {
            Field[] fields = FieldUtils.getAllFields(mappedClass);
            Map<String, String> mapField = new HashMap<>();
            for (Field field : fields) {
                mapField.put(field.getName(), field.getName());
            }
            String orderBy = " ORDER BY ";
            if ("asc".equalsIgnoreCase(sortType)) {
                sqlQueryResult.append(orderBy).append(mapField.get(sortName)).append(" ASC");
            } else if ("desc".equalsIgnoreCase(sortType)) {
                sqlQueryResult.append(orderBy).append(mapField.get(sortName)).append(" DESC");
            } else {
                sqlQueryResult.append(orderBy).append(mapField.get(sortName));
            }
        }
        sqlQueryResult.append(" ) bcd LIMIT :p_page_length offset :p_page_number ) T_TABLE_NAME, ").append(" ( SELECT COUNT(*) totalRow FROM ( ").append(sqlQuery).append(" ) T_TABLE_TOTAL ) ").append("T_TABLE_NAME_TOTAL ");
        parameters.put("p_page_number", (page - 1) * pageSize);
        parameters.put("p_page_length", pageSize);
        List<?> list = getNamedParameterJdbcTemplate().query(sqlQueryResult.toString(), parameters, BeanPropertyRowMapper.newInstance(mappedClass));
        int count = 0;
        if (list.isEmpty()) {
            dataReturn.setTotal(count);
        } else {
            try {
                Object obj = list.get(0);
                Field field = obj.getClass().getSuperclass().getDeclaredField("totalRow");
                count = Integer.parseInt(field.get(obj).toString());
                dataReturn.setTotal(count);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (pageSize > 0) {
            if (count % pageSize == 0) {
                dataReturn.setPages(count / pageSize);
            } else {
                dataReturn.setPages((count / pageSize) + 1);
            }
        }
        dataReturn.setData(list);
        return dataReturn;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(Class<T> persistentClass) {
        return entityManager.createQuery(" SELECT t FROM " + persistentClass.getSimpleName() + " t").getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<T> findByMultiParam(Class<T> persistentClass, Object... params) {
        Map<String, Object> mapParams = new HashMap<>();
        StringBuilder sqlQuery = new StringBuilder(" SELECT t FROM " + persistentClass.getSimpleName() + " t WHERE 1 = 1 ");
        if (params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                if (i % 2 == 0) {
                    sqlQuery.append(" AND t.").append(params[i]).append(" = :p_").append(params[i]).append(" ");
                    mapParams.put("p_" + params[i], params[i + 1]);
                }
            }
        }
        Query query = entityManager.createQuery(sqlQuery.toString());
        for (Map.Entry<String, Object> entry : mapParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        return query.getResultList();
    }
}
