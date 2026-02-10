package com.mt.invoice.controller;

import com.mt.invoice.model.InvoiceInfo;
import com.mt.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/parseInvoices")
    public List<InvoiceInfo> parseInvoices(@RequestBody String text) {
        List<String> urls = new ArrayList<>();
        urls.add("http://cyyzerp.cysignet.com/17701006395480078.pdf");
        urls.add("http://cyyzerp.cysignet.com/17702001860750078.pdf");
        urls.add("http://cyyzerp.cysignet.com/17702001909360078.pdf");
        urls.add("http://cyyzerp.cysignet.com/17702001981780078.pdf");
        urls.add("http://cyyzerp.cysignet.com/17702002037040078.pdf");
        urls.add("http://cyyzerp.cysignet.com/17702002134100078.pdf");
        urls.add("http://cyyzerp.cysignet.com/17702002212110078.pdf");
        List<InvoiceInfo> invoiceInfos = new ArrayList<>();
        for (String url : urls) {
            InvoiceInfo invoiceInfo = invoiceService.parseInvoice(url);
            invoiceInfos.add(invoiceInfo);
        }

        return invoiceInfos;
    }
}
