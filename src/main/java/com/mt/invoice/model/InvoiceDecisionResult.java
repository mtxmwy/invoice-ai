package com.mt.invoice.model;

public class InvoiceDecisionResult {
    private boolean approved;
    private String reason;

    public InvoiceDecisionResult() {
    }

    public InvoiceDecisionResult(boolean approved, String reason) {
        this.approved = approved;
        this.reason = reason;
    }

    public static InvoiceDecisionResult approved() {
        return new InvoiceDecisionResult(true, null);
    }

    public static InvoiceDecisionResult needReview(String reason) {
        return new InvoiceDecisionResult(false, reason);
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}