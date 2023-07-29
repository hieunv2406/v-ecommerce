package com.vm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class PageDto {

    private Integer page;
    private Integer pageSize;
    private List<Map<String, String>> sortNameAndTypeList;
    private String sortName;
    private String sortType;
    private String langKey;
    private String timeOffset;
    private String sqlQuery;
    private List<Long> listId;
    private Map<String, Object> parameters;
    private String searchAll;
    private String proxyLocale;
    private String resultImport;

}
