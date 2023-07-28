package com.vm.repository;

import com.vm.entities.Cart;
import com.vm.entities.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long>, PagingAndSortingRepository<CartProduct, Long> {
    List<CartProduct> findByCart(Cart cart);
}
