package com.vm.dto.cart;

import com.vm.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private String userName;

    private List<ProductDto> products;

    @Override
    public String toString() {
        return "CartDto{" +
                "userName='" + userName + '\'' +
                ", products=" + products +
                '}';
    }
}
