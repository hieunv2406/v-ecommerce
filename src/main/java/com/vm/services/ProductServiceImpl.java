package com.vm.services;

import com.vm.dto.product.ProductDto;
import com.vm.entities.Product;
import com.vm.repository.CouponRepository;
import com.vm.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Override
    @Transactional
    public ProductDto save(ProductDto productDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        Product product = productDto.toEntity();
        couponRepository.save(productDto.getCoupon());
        product.setCreatedBy(username);
        productRepository.save(product);
        return productDto;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findAll(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("createdDate").ascending());
        Page<Product> products = productRepository.findAll(paging);
        return products.getContent();
    }

}
