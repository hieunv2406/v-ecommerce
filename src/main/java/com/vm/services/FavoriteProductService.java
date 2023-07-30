package com.vm.services;

import com.vm.dto.favorite.FavoriteProductRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.FavoriteProduct;
import com.vm.exceptions.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavoriteProductService {
    ResultDto addNewFavoriteProduct(FavoriteProductRequest favoriteProductRequest) throws CustomException;
    Page<FavoriteProduct> getListFavoriteProduct(Pageable pageable) throws CustomException;
}
