package com.vm.controller;

import com.vm.dto.CartProductRequest;
import com.vm.dto.ResultDto;
import com.vm.services.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/cartProduct")
public class CartProductController {

    @Autowired
    private CartProductService cartProductService;

    @DeleteMapping(path = "/removeProductFromCart/{id}")
    public ResponseEntity<ResultDto> removeProductFromCart(@PathVariable("id") Long cartProductId) {
        ResultDto resultDto = cartProductService.removeProductFromCart(cartProductId);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PutMapping(path = "/editCartProduct")
    public ResponseEntity<ResultDto> editCartProduct(@Valid @RequestBody CartProductRequest cartProductRequest) {
        ResultDto resultDto = cartProductService.editCartProduct(cartProductRequest);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }
}
