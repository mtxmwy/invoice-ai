package com.mt.invoice.agent;

import com.mt.invoice.model.InvoiceInfo;
import dev.langchain4j.agent.tool.Tool;

public class InvoiceDecisionTools {
    @Tool("确认发票识别结果可信，可以自动入库")
    public void approveInvoice(InvoiceInfo invoice) {
        // 这里只做标记，不直接入库
        System.out.println("✅ 发票通过自动处理：" + invoice.invoiceNo);
    }

    @Tool("发票信息存在风险，需要人工复核")
    public void requestHumanReview(InvoiceInfo invoice, String reason) {
        System.out.println("⚠️ 进入人工复核：" + reason);
    }
}
