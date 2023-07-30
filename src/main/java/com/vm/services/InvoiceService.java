package com.vm.services;

import com.vm.dto.Datatable;
import com.vm.dto.invoice.InvoiceRequest;
import com.vm.dto.ResultDto;

public interface InvoiceService {
    ResultDto createInvoice(InvoiceRequest invoiceRequest) throws Exception;

    Datatable getListInvoicePage(InvoiceRequest invoiceRequest);
}
