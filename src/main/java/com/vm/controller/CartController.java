package com.vm.controller;

import com.vm.dto.CartProductRequest;
import com.vm.dto.ResultDto;
import com.vm.dto.cart.CartDto;
import com.vm.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(path = "/getCartByUser")
    public ResponseEntity<CartDto> getCartByUser() {
        CartDto cartResponse = cartService.getCartByUser();
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/addNewProductToCart")
    public ResponseEntity<ResultDto> addNewProductToCart(@Valid @RequestBody CartProductRequest cartProductRequest) {
        ResultDto resultDto = cartService.addNewProductToCart(cartProductRequest);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}
