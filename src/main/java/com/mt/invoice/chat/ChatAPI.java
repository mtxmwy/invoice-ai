package com.mt.invoice.chat;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class ChatAPI {

    private final ChatModel chatModel;

    @GetMapping("low/chat")
    public String lowChat(@RequestParam("message") String message) {
        String response = chatModel.chat(List.of(SystemMessage.systemMessage("假设你是一个雅思专家，请你帮助我学习英语"), UserMessage.from(message))).aiMessage().text();
        return response;
    }
}
