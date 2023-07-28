package com.vm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FavoriteProductRequest {
    @NotNull
    private Long productId;
}
