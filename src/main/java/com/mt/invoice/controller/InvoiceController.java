package com.mt.invoice.controller;

import com.mt.invoice.model.InvoiceInfo;
import com.mt.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/parse")
    public InvoiceInfo parse(@RequestBody String text) {

        return invoiceService.parseInvoice("http://cyyzerp.cysignet.com/17701006395480078.pdf");
    }
}
