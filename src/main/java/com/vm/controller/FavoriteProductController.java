package com.vm.controller;

import com.vm.dto.FavoriteProductRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.FavoriteProduct;
import com.vm.exceptions.UserNotFoundException;
import com.vm.services.FavoriteProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/favorite")
public class FavoriteProductController {
    @Autowired
    private FavoriteProductService favoriteProductService;

    @PostMapping(path = "/add")
    public ResponseEntity<ResultDto> addNewFavoriteProduct(@RequestBody @Valid FavoriteProductRequest favoriteProductRequest) throws Exception {
        log.info("add new favorite product");
        ResultDto resultDto = favoriteProductService.addNewFavoriteProduct(favoriteProductRequest);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<Page<FavoriteProduct>> getListFavoriteProduct(Pageable pageable) throws UserNotFoundException {
        log.info("get list favorite product");
        return new ResponseEntity<>(favoriteProductService.getListFavoriteProduct(pageable), HttpStatus.OK);
    }
}
