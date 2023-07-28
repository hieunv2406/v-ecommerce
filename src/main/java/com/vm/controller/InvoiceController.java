package com.vm.controller;

import com.vm.dto.Datatable;
import com.vm.dto.InvoiceRequest;
import com.vm.dto.ResultDto;
import com.vm.services.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(path = "/add")
    public ResponseEntity<ResultDto> createInvoice(@RequestBody @Valid InvoiceRequest invoiceRequest) throws Exception {
        log.info("Create new invoice");
        ResultDto resultDto = invoiceService.createInvoice(invoiceRequest);
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @PostMapping(path = "/search")
    public ResponseEntity<Datatable> getListInvoicePage(@RequestBody InvoiceRequest invoiceRequest) {
        log.info("searching Invoice");
        Datatable data = invoiceService.getListInvoicePage(invoiceRequest);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
