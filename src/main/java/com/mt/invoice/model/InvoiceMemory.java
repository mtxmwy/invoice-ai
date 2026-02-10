package com.mt.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceMemory {

    private Long id;

    // OCR / PDF 解析后的文本
    private String rawText;

    // 向量
    private float[] embedding;

    // 成功的结构化结果
    private String invoiceJson;

    // 易错提示
    private String riskNotes;

    // human / auto
    private String verifiedBy;
}
