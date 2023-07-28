package com.vm.dto.product;

import com.vm.entities.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long productId;

    private String productName;

    private Double price;

    private int inventory;

    private Category category;

    private List<InvoiceItem> invoiceItems;

    private Coupon coupon;

    private boolean inStock = true;

    private Long quantity;

    private Long status;

    public ProductDto(Long productId, String productName, Double price, int inventory, Category category, List<InvoiceItem> invoiceItems,
                      Coupon coupon, boolean inStock) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.inventory = inventory;
        this.category = category;
        this.invoiceItems = invoiceItems;
        this.coupon = coupon;
        this.inStock = inStock;
    }

    public Product toEntity() {
        return new Product(productId, productName, price, inventory, category, invoiceItems, coupon, inStock);
    }
}
