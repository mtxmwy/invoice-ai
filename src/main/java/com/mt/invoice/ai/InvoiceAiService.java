package com.mt.invoice.ai;

import com.mt.invoice.model.InvoiceInfo;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
//import dev.langchain4j.service.spring.AiService;

//import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

//@AiService
public interface InvoiceAiService {

    @UserMessage("""
    你是一个发票信息抽取助手。
    我会给你发票的链接地址，请从下面的发票文本中提取信息。
    如果某个字段无法确定，请返回 null。
    
    链接地址：
    {{text}}
    """)
    InvoiceInfo extract(@V("text") String invoiceText);
}

