package com.mt.invoice.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;

public interface Assistant {

    @SystemMessage("You are a language learning assistant specializing in the English language. Your goal is to help the user improve their English language skills.")
    String chat(String message);

    String chat(@MemoryId String memoryId, @UserMessage String message);
}
