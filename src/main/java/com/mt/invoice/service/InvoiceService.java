package com.mt.invoice.service;


import com.mt.invoice.ai.InvoiceAiService;
import com.mt.invoice.model.InvoiceInfo;
import com.mt.invoice.util.InvoiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceAiService aiService;



    public InvoiceInfo parseInvoice(String text) {
        InvoiceInfo info = aiService.extract(text);

        InvoiceValidator.validate(info);

        return info;
    }
}
