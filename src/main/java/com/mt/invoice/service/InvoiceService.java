package com.mt.invoice.service;


import com.alibaba.fastjson2.JSONObject;
import com.mt.invoice.ai.InvoiceAiService;
import com.mt.invoice.ai.InvoiceDecisionAgent;
import com.mt.invoice.model.InvoiceDecisionResult;
import com.mt.invoice.model.InvoiceInfo;
import com.mt.invoice.model.InvoiceMemory;
import com.mt.invoice.util.FileDownloader;
import com.mt.invoice.util.InvoiceValidator;
import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentLoader;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.PdfFileContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.embedding.EmbeddingModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class InvoiceService {

    @Autowired
    private InvoiceAiService aiService;


    @Autowired
    private InvoiceDecisionAgent decisionAgent;

    @Autowired
    private FileDownloader fileDownloader;

    @Autowired
    private QwenEmbeddingModel embeddingModel;

    @Autowired
    private InvoiceMemoryRepository invoiceMemoryRepository;






    public InvoiceInfo parseInvoice(String url) {

        // 1. 下载文件
        byte[] fileBytes = fileDownloader.download(url);

        DocumentParser parser = new ApachePdfBoxDocumentParser();
        Document document = parser.parse(new ByteArrayInputStream(fileBytes));

        log.info("提取前内容为：{}", document.text());

        // 1. 生成向量
        float[] queryVector = embed(document.text());

        // 2. 查历史案例
        List<InvoiceMemory> memories =
                invoiceMemoryRepository.search(queryVector, 3);
        // 第一次提取，使用基础版本的extract方法，不需要反馈
        InvoiceInfo info = aiService.extract(document.text());
        log.info("提取后内容为：{}", JSONObject.toJSONString(info));
        
        // 处理AI决策结果
        InvoiceDecisionResult decisionResult = decisionAgent.decide(JSONObject.toJSONString(info),new Date());

        if (!decisionResult.isApproved()) {
            log.warn("发票未通过：{}", decisionResult.getReason());
            // 可以在这里添加额外的处理逻辑
            // 使用带反馈的版本重新提取
            InvoiceInfo revised = aiService.extract(document.text(), decisionResult.getReason());

            InvoiceDecisionResult finalDecision = decisionAgent.decide(JSONObject.toJSONString(revised),new Date());
            if (!finalDecision.isApproved()) {
                log.warn("发票仍然需要人工复核：{}", finalDecision.getReason());
                // 可以在这里添加额外的处理逻辑
            }else{
                log.info("发票通过：{}", finalDecision.getReason());
            }
        }else{
            log.info("发票通过：{}", decisionResult.getReason());
        }
        
        InvoiceValidator.validate(info);

        return info;
    }

    public float[] embed(String text) {
        return embeddingModel.embed(text).content().vector();
    }
}
