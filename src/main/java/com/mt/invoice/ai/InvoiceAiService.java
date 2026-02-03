package com.mt.invoice.ai;

import com.mt.invoice.model.InvoiceInfo;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.PdfFileContent;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
//import dev.langchain4j.service.spring.AiService;

//import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

//@AiService
public interface InvoiceAiService {

    @UserMessage("""
    你是一个发票识别助手。
    以下文本需要你提取相关信息。
    如：发票号码、开票日期、购买方信息、合计金额等。
    如果某个字段无法确定，请返回 null。
    
    内容为：
    {{text}}
    """)
    InvoiceInfo extract(@V("text") String text);


    //下面的是升级版，：动态 Prompt 设计
    @UserMessage("""
            你是一个发票信息抽取助手。
            请从下面的发票内容中提取结构化发票信息。
            
            {% if feedback != null %}
            请特别注意以下复核反馈，并据此修正结果：
            {{feedback}}
            {% endif %}
            
            发票内容：
            {{text}}
            
            要求：
            - 严格按照字段定义输出
            - 不要猜测无法确认的信息
            """)
    InvoiceInfo extract(
            @V("text") String invoiceText,
            @V("feedback") String feedback
    );
}

