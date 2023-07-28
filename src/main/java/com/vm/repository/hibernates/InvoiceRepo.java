package com.vm.repository.hibernates;

import com.vm.dto.Datatable;
import com.vm.dto.InvoiceRequest;

public interface InvoiceRepo {
    Datatable getListInvoicePage(InvoiceRequest invoiceRequest);
}
