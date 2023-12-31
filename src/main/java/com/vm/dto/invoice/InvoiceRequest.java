package com.vm.dto.invoice;

import com.vm.dto.PageDto;
import com.vm.entities.InvoiceItem;
import lombok.Data;

import java.util.List;

@Data
public class InvoiceRequest extends PageDto {
    private Long invoiceId;
    private Double totalAmount;
    private Boolean status;
    private String note;
    private List<InvoiceItem> invoiceItems;
}
