package com.vm.dto.category;

import com.vm.dto.PageDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorySearchRequest extends PageDto {
    private String categoryName;
    private String description;
    private Date createdAt;
}
