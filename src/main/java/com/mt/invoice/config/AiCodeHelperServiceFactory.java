package com.mt.invoice.config;

import com.mt.invoice.ai.InvoiceAiService;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiCodeHelperServiceFactory {
    @Resource(name = "qwenChatModel")
    private ChatModel qwenChatModel;

    /**
     * AiServices.create就相当于提供反射机制来实现了一个代理对象,
     * 用来输入输出,用户传递的消息转换为userMessage,并调用chatModel,
     * 并返回结果AIMessage消息转换为String类型给用户
     */
    @Bean
    public InvoiceAiService aiCodeHelperServiceimpl() {
        return AiServices.create(InvoiceAiService.class, qwenChatModel);
    }
}

