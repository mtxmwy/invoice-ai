package com.mt.invoice.config;

import com.mt.invoice.agent.InvoiceDecisionTools;
import com.mt.invoice.ai.InvoiceAiService;
import com.mt.invoice.ai.InvoiceDecisionAgent;
import com.mt.invoice.service.Assistant;
import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
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


    @Bean
    public InvoiceDecisionAgent invoiceDecisionServiceimpl() {
        InvoiceDecisionAgent agent = AiServices.builder(InvoiceDecisionAgent.class)
                .chatModel(qwenChatModel)
                // 注意：Qwen3-VL-Plus等多模态模型目前不支持工具，所以不添加.tools()参数
              //  .tools(new InvoiceDecisionTools())
                .build();
        return agent;
    }

    @Bean
    public QwenEmbeddingModel qwenEmbeddingModel() {
        return QwenEmbeddingModel.builder()
                .apiKey("sk-498167e2ac7e46e898514bdf692bc543")
                .modelName("qwen-vl-plus") // Changed from .modelName() to .modelName("qwen-plus")
                .build();
    }

    @Bean
    public Assistant assistant() {
        Assistant assistant = AiServices.builder(Assistant.class)
                .chatMemoryProvider(memoryId-> MessageWindowChatMemory.withMaxMessages(10))
                .chatModel(qwenChatModel)
                // 注意：Qwen3-VL-Plus等多模态模型目前不支持工具，所以不添加.tools()参数
                //  .tools(new InvoiceDecisionTools())
                .build();
        return assistant;
    }


}

