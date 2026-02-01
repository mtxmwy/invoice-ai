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
        return invoiceService.parseInvoice("https://dppt.shenzhen.chinatax.gov.cn:8443/kpfw/fpjfzz/v1/exportDzfpwjEwm?Wjgs=pdf&Jym=E095&Fphm=26952000000416516581&Kprq=20260201103302&Czsj=1769913182598");
    }
}
