package com.mt.invoice.ai;

import com.mt.invoice.model.InvoiceDecisionResult;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

import java.util.Date;

public interface InvoiceDecisionAgent {

    @SystemMessage("""
        你是一个发票审核决策智能体。
        你的任务不是修改数据，而是判断当前发票识别结果是否可信。
        请直接返回JSON格式的决策结果，包含是否批准以及原因。
        """)
    @UserMessage("""
        以下是已经识别出的发票信息：
        {{invoice}}
        【系统事实】
            - 当前系统日期：{{now}}
            - 发票号码长度为20,这是后面的全电发票新规则.
        请判断该结果是否可信：
        【复核规则】 
        1. 发票日期晚于当前系统日期，才属于未来日期。发票日期早于系统时间是正常的。
        - 如果信息完整且金额、税率、金额合理，请批准。不需要校验其他未提及的信息
        - 只需要校验发票号码长度，其他无需校验
        - 如果存在不确定或风险点，请进入人工复核，并说明原因（低风险直接通过）
        """)
    InvoiceDecisionResult decide(@V("invoice") String invoice, @V("now") Date now);
}
