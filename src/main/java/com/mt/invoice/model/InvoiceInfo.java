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

    @Description("购买方信息，对应的名称")
    public String buyerName;



    @Description("税率/征收税，例如 6%")
    public BigDecimal taxRate;

    @Description("税额")
    public BigDecimal taxAmount;

    @Description("金额")
    public BigDecimal amount;


    @Description("找到价税合计（大写） 在右边的找对应的小写")
    @NotNull
    public BigDecimal totalAmount;
}
