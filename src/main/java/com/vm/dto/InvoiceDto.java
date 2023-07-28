package com.vm.dto;

import com.vm.entities.InvoiceItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {
    private Long invoiceId;
    private Double totalAmount;
    private Boolean status;
    private String note;
    private Long userId;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
    private List<InvoiceItem> invoiceItems;
}
