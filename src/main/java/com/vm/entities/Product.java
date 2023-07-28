package com.vm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vm.dto.product.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "ProductName")
    private String productName;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Inventory")
    private int inventory;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvoiceItem> invoiceItems;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "couponId")
    private Coupon coupon;

    @Transient
    private boolean inStock = true;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartProduct> cartProducts;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoriteProduct> favoriteProducts;

    public Product(Long productId, String productName, Double price, int inventory, Category category, List<InvoiceItem> invoiceItems,
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

    public ProductDto toDto() {
        return new ProductDto(productId, productName, price, inventory, category, invoiceItems, coupon, inStock);
    }
}
