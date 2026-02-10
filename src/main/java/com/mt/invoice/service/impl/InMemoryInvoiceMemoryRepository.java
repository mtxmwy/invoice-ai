package com.mt.invoice.service.impl;

import com.mt.invoice.model.InvoiceMemory;
import com.mt.invoice.service.InvoiceMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class InMemoryInvoiceMemoryRepository implements InvoiceMemoryRepository {

    private final List<InvoiceMemory> store = new ArrayList<>();

    @Override
    public void save(InvoiceMemory memory) {
        store.add(memory);
    }

    @Override
    public List<InvoiceMemory> search(float[] embedding, int topK) {

        return store.stream()
                .sorted(Comparator.comparingDouble(m ->
                        -cosineSimilarity(embedding, m.getEmbedding())))
                .limit(topK)
                .toList();
    }

    private double cosineSimilarity(float[] v1, float[] v2) {
        double dot = 0.0, normA = 0.0, normB = 0.0;
        for (int i = 0; i < v1.length; i++) {
            dot += v1[i] * v2[i];
            normA += v1[i] * v1[i];
            normB += v2[i] * v2[i];
        }
        return dot / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
