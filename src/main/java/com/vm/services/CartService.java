package com.vm.services;

import com.vm.dto.cartproduct.CartProductRequest;
import com.vm.dto.ResultDto;
import com.vm.dto.cart.CartDto;

public interface CartService {
    CartDto getCartByUser();
    ResultDto addNewProductToCart(CartProductRequest cartProductRequest);
}
