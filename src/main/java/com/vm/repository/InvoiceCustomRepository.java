package com.vm.repository;

import com.vm.dto.Datatable;
import com.vm.dto.InvoiceRequest;

public interface InvoiceCustomRepository {
    Datatable getListInvoicePage(InvoiceRequest invoiceRequest);
}
