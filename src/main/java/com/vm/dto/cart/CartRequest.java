package com.vm.dto.cart;

import lombok.Data;

@Data
public class CartRequest {
    private Long cartId;
    private String status;
}
