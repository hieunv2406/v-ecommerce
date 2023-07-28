package com.vm.services;

import com.vm.dto.CartProductRequest;
import com.vm.dto.ResultDto;
import com.vm.dto.cart.CartDto;
import com.vm.entities.Cart;

import java.util.Optional;

public interface CartService {
    CartDto getCartByUser();
    ResultDto addNewProductToCart(CartProductRequest cartProductRequest);
}
