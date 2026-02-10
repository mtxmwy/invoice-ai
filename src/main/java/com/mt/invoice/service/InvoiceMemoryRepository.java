package com.mt.invoice.service;

import com.mt.invoice.model.InvoiceMemory;

import java.util.List;

public interface InvoiceMemoryRepository {

    void save(InvoiceMemory memory);

    List<InvoiceMemory> search(float[] embedding, int topK);
}

