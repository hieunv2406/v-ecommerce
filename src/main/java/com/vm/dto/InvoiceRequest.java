package com.vm.dto;

import com.vm.entities.InvoiceItem;
import lombok.Data;

import java.util.List;
@Data
public class InvoiceRequest extends BaseDto {
    private Long invoiceId;
    private Double totalAmount;
    private Boolean status;
    private String note;
    private List<InvoiceItem> invoiceItems;
}
