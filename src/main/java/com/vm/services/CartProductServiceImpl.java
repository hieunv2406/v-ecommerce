package com.vm.services;

import com.vm.dto.CartProductRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.CartProduct;
import com.vm.repository.CartProductRepository;
import com.vm.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartProductServiceImpl implements CartProductService {

    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public ResultDto removeProductFromCart(Long cartProductId) {
        Optional<CartProduct> cartProduct = cartProductRepository.findById(cartProductId);
        if (cartProduct.isPresent()) {
            cartProductRepository.delete(cartProduct.get());
            return new ResultDto(Constants.ResponseKey.SUCCESS);
        }
        return new ResultDto(Constants.ResponseKey.ERROR, "cartProduct", "not found!");
    }

    @Override
    public ResultDto editCartProduct(CartProductRequest cartProductRequest) {
        Optional<CartProduct> cartProduct = cartProductRepository.findById(cartProductRequest.getCartProductId());
        if (cartProduct.isPresent()) {
            cartProduct.get().setQuantity(cartProductRequest.getQuantity());
            cartProduct.get().setStatus(cartProductRequest.getStatus() != null ? cartProductRequest.getStatus() : null);
            cartProductRepository.save(cartProduct.get());
            return new ResultDto(Constants.ResponseKey.SUCCESS);
        }
        return new ResultDto(Constants.ResponseKey.ERROR, "cartProduct", "not found!");
    }
}
