package org.dromara.web.controller;


import cn.dev33.satoken.annotation.SaIgnore;


import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;

import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.service.AiServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SaIgnore
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatLanguageModel chatLanguageModel;
    @GetMapping("/lowChat")
    public String lowChat(@RequestParam("message") String message){
        // 构建带系统指令的消息链
        SystemMessage systemMsg = SystemMessage.from("你是一个新能源汽车专家，用中文回答，分点说明优势，每点附带实际数据");
        UserMessage userMsg = UserMessage.from(message); // 使用传入的message参数
        AiMessage aiMessage = chatLanguageModel.chat(systemMsg, userMsg)
            .aiMessage();

        return aiMessage.text();
    }

    @GetMapping("/highChat")
    public String highChat(@RequestParam("message") String message) {
        return AiServices.create(EnergyCarAssistant.class, chatLanguageModel).explainAdvantages(message);
    }

}
