package com.vm.controller;

import com.vm.dto.product.ProductDto;
import com.vm.entities.Product;
import com.vm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/api/create-product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.save(productDto), HttpStatus.OK);
    }

    @GetMapping("/api/product/{id}")
    public ResponseEntity<Product> findProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/api/products")
    public ResponseEntity<List<Product>> findProduct(@RequestParam int pageNo, @RequestParam int pageSize) {
        return ResponseEntity.ok(productService.findAll(pageNo, pageSize));
    }
}
