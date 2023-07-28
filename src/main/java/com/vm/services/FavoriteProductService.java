package com.vm.services;

import com.vm.dto.FavoriteProductRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.FavoriteProduct;
import com.vm.exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavoriteProductService {
    ResultDto addNewFavoriteProduct(FavoriteProductRequest favoriteProductRequest) throws Exception;
    Page<FavoriteProduct> getListFavoriteProduct(Pageable pageable) throws UserNotFoundException;
}
