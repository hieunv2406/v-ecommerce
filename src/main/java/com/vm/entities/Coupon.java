package com.vm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    private String couponName;

    @JsonManagedReference
    @OneToMany(mappedBy = "coupon")
    @JsonIgnore
    private List<Product> products;

    @JsonManagedReference
    @OneToMany(mappedBy = "coupon")
    @JsonIgnore
    private List<InvoiceItem> invoiceItems;
}
