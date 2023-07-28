package com.vm.services;

import com.vm.dto.CartProductRequest;
import com.vm.dto.ResultDto;

public interface CartProductService {
    ResultDto removeProductFromCart(Long cartProductId);

    ResultDto editCartProduct(CartProductRequest cartProductRequest);
}
