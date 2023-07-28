package com.vm.repository;

import com.vm.entities.FavoriteProduct;
import com.vm.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long>, PagingAndSortingRepository<FavoriteProduct, Long> {
    Page<FavoriteProduct> findByUser(User user, Pageable pageable);
}
