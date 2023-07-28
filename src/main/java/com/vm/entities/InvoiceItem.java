package com.vm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceItemId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "TotalAmount")
    private Double totalAmount;

    @Column(name = "Status")
    private Boolean status;

    @Column(name = "Note")
    private String note;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "invoiceId", nullable = false)
    private Invoice invoice;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "couponId")
    private Coupon coupon;
}
