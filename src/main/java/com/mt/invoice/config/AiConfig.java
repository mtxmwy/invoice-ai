package com.mt.invoice.config;


import com.mt.invoice.ai.InvoiceAiService;

import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

//    @Bean
//    public InvoiceAiService invoiceAiService() {
//        ChatLanguageModel model = .builder()
//                .apiKey(System.getenv("OPENAI_API_KEY"))
//                .modelName("gpt-4o-mini")
//                .temperature(0)
//                .build();
//
//        return AiServices.create(InvoiceAiService.class, model);
//    }
}

