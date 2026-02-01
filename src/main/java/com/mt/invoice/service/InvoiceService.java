package com.mt.invoice.service;


import com.alibaba.fastjson2.JSONObject;
import com.google.gson.JsonObject;
import com.mt.invoice.ai.InvoiceAiService;
import com.mt.invoice.model.InvoiceInfo;
import com.mt.invoice.util.InvoiceValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InvoiceService {

    @Autowired
    private InvoiceAiService aiService;



    public InvoiceInfo parseInvoice(String text) {
        InvoiceInfo info = aiService.extract(text);
        log.info("解析内容为：{}", JSONObject.toJSONString(info));
        InvoiceValidator.validate(info);

        return info;
    }
}
