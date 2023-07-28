package com.vm.services;

import com.vm.dto.Datatable;
import com.vm.dto.InvoiceRequest;
import com.vm.dto.ResultDto;
import com.vm.entities.Invoice;
import com.vm.entities.InvoiceItem;
import com.vm.entities.User;
import com.vm.exceptions.InvoiceItemInValidException;
import com.vm.exceptions.UserNotFoundException;
import com.vm.repository.InvoiceRepository;
import com.vm.repository.UserRepository;
import com.vm.repository.hibernates.InvoiceRepo;
import com.vm.utils.AuthUtil;
import com.vm.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InvoiceRepo invoiceRepo;

    @Override
    public ResultDto createInvoice(InvoiceRequest invoiceRequest) throws Exception {
        Optional<User> user = userRepository.findByUsername(AuthUtil.getUsernameFromJwtToken());
        if (!user.isPresent()) {
            log.error("User not found");
            throw new UserNotFoundException("User not found");
        }
        if (!invoiceRequest.getInvoiceItems().isEmpty()) {
            log.error("Invoice item invalid");
            throw new InvoiceItemInValidException("Invoice item invalid");
        }
        List<InvoiceItem> invoiceItems = invoiceRequest.getInvoiceItems().stream().map(
                o -> {
                    InvoiceItem invoiceItem = new InvoiceItem();
                    invoiceItem.setTotalAmount(o.getTotalAmount());
                    invoiceItem.setQuantity(o.getQuantity());
                    invoiceItem.setStatus(o.getStatus());
                    invoiceItem.setNote(o.getNote());
                    invoiceItem.setProduct(o.getProduct());
                    return invoiceItem;
                }
        ).collect(Collectors.toList());
        Invoice invoice = new Invoice();
        invoice.setTotalAmount(invoiceRequest.getTotalAmount());
        invoice.setStatus(invoiceRequest.getStatus());
        invoice.setNote(invoiceRequest.getNote());
        invoice.setUser(user.get());
        invoice.setInvoiceItems(invoiceItems);
        Invoice invoiceResult = invoiceRepository.save(invoice);
        return new ResultDto(Constants.ResponseKey.SUCCESS, null, invoiceResult);
    }

    @Override
    public Datatable getListInvoicePage(InvoiceRequest invoiceRequest) {
        return invoiceRepo.getListInvoicePage(invoiceRequest);
    }
}
