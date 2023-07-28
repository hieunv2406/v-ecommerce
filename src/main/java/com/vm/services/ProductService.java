package com.vm.services;

import com.vm.dto.product.ProductDto;
import com.vm.entities.Product;

import java.util.List;

public interface ProductService {
    ProductDto save(ProductDto productDto);
    Product findById(Long id);
    List<Product> findAll(int pageNo, int pageSize);
}
