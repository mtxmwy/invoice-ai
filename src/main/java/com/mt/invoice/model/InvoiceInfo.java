package com.mt.invoice.model;
import dev.langchain4j.model.output.structured.Description;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



import java.math.BigDecimal;

public class InvoiceInfo {

    @Description("发票号码")
    @NotBlank
    public String invoiceNo;

    @Description("开票日期，格式 yyyy-MM-dd")
    public String invoiceDate;

    @Description("开票方名称")
    public String sellerName;

    @Description("含税总金额，单位人民币元")
    @NotNull
    public BigDecimal totalAmount;

    @Description("税率，例如 0.13")
    public BigDecimal taxRate;
}
