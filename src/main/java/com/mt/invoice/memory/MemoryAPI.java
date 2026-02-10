package com.mt.invoice.memory;


import com.mt.invoice.service.Assistant;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/memory")
@AllArgsConstructor
public class MemoryAPI {

    private final ChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

    private final ChatModel chatModel;

    private final Assistant assistant;

    private final Map<String, ChatMemory> chatMemories = new ConcurrentHashMap<>();

    @GetMapping("low/chat")
    public String lowChat(@RequestParam("message") String message) {
        chatMemory.add(UserMessage.from(message));
        String response = chatModel.chat(chatMemory.messages()).aiMessage().text();
        chatMemory.add(UserMessage.from(response));
        return response;
    }



    @GetMapping("high/chat")
    public String highChat(@RequestParam() String memoryId, @RequestParam("message") String message) {
        if (!chatMemories.containsKey(memoryId)) {
            MessageWindowChatMemory memory = MessageWindowChatMemory.withMaxMessages(10);
            memory.add(UserMessage.from(message));
            chatMemories.put(memoryId, memory);
        }
        List<ChatMessage> messages = chatMemories.get(memoryId).messages();

        String response = chatModel.chat(messages).aiMessage().text();
        chatMemories.get(memoryId).add(UserMessage.from(response));
        return response;
    }
}
