package com.mt.invoice.util;


import com.mt.invoice.model.InvoiceInfo;

public class InvoiceValidator {

    public static void validate(InvoiceInfo info) {
        if (info.invoiceNo == null || info.invoiceNo.isBlank()) {
            throw new IllegalArgumentException("发票号码缺失，需要人工复核");
        }

        if (info.totalAmount == null || info.totalAmount.signum() <= 0) {
            throw new IllegalArgumentException("金额异常");
        }
    }
}

