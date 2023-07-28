package com.vm.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartProductRequest {
    private Long cartProductId;
    private Long productId;
    @NotNull
    private Long quantity;
    private Long status;
}
