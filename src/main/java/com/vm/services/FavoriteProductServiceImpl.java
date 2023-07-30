package com.vm.services;

import com.vm.dto.favorite.FavoriteProductRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.FavoriteProduct;
import com.vm.entities.Product;
import com.vm.entities.User;
import com.vm.exceptions.CustomException;
import com.vm.repository.FavoriteProductRepository;
import com.vm.repository.ProductRepository;
import com.vm.repository.UserRepository;
import com.vm.utils.AuthUtil;
import com.vm.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FavoriteProductServiceImpl implements FavoriteProductService {

    @Autowired
    private FavoriteProductRepository favoriteProductRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResultDto addNewFavoriteProduct(FavoriteProductRequest favoriteProductRequest) throws CustomException {
        Optional<User> user = userRepository.findByUsername(AuthUtil.getUsernameFromJwtToken());
        if (!user.isPresent()) {
            log.error("User not found");
            throw new CustomException("User not found");
        }
        Optional<Product> product = productRepository.findById(favoriteProductRequest.getProductId());
        if (!product.isPresent()) {
            log.error("Product not found");
            throw new CustomException("Product not found");
        }
        FavoriteProduct favoriteProduct = new FavoriteProduct();
        favoriteProduct.setProduct(product.get());
        favoriteProduct.setUser(user.get());
        FavoriteProduct favoriteProductResult = favoriteProductRepository.save(favoriteProduct);
        return new ResultDto(Constants.ResponseKey.SUCCESS, null, favoriteProductResult);
    }

    @Override
    public Page<FavoriteProduct> getListFavoriteProduct(Pageable pageable) throws CustomException {
        Optional<User> user = userRepository.findByUsername(AuthUtil.getUsernameFromJwtToken());
        if (!user.isPresent()) {
            log.error("User not found");
            throw new CustomException("User not found");
        }
        return favoriteProductRepository.findByUser(user.get(), pageable);
    }
}
