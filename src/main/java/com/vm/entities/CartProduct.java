package com.vm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class CartProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartProductId")
    private Long cartProductId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "status")
    private Long status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cartId", nullable = false)
    @JsonIgnore
    private Cart cart;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    @JsonIgnore
    private Product product;

}
