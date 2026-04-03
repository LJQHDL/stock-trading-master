package org.dromara.web.controller;

import cn.dev33.satoken.annotation.SaIgnore;

import dev.langchain4j.model.chat.ChatLanguageModel;
import io.github.pigmesh.ai.deepseek.core.DeepSeekClient;
import io.github.pigmesh.ai.deepseek.core.chat.ChatCompletionModel;
import io.github.pigmesh.ai.deepseek.core.chat.ChatCompletionRequest;
import io.github.pigmesh.ai.deepseek.core.chat.ChatCompletionResponse;
import io.github.pigmesh.ai.deepseek.core.chat.ResponseFormatType;
import io.github.pigmesh.ai.deepseek.core.models.ModelsResponse;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import org.springframework.http.MediaType;

@RestController
@RequestMapping
public class DeepController {

    @Autowired
    private DeepSeekClient deepSeekClient;



    @SaIgnore
    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatCompletionResponse> chat(String prompt) {
        return deepSeekClient.chatFluxCompletion(prompt);
    }

    @SaIgnore
    @GetMapping(value = "/highchat")
    public ChatCompletionResponse syncChat(String prompt) {
        String systemPrompt = "   The user will provide some exam text. Please parse the \"question\" and \"answer\" and output them in JSON format.\n" +
            "            \n" +
            "            EXAMPLE INPUT:\n" +
            "            Which is the highest mountain in the world? Mount Everest.\n" +
            "            \n" +
            "            EXAMPLE JSON OUTPUT:\n" +
            "            {\n" +
            "                \"question\": \"Which is the highest mountain in the world?\",\n" +
            "                \"answer\": \"Mount Everest\"\n" +
            "            }";



        String userPrompt = "Which is the longest river in the world? The Nile River.";


        ChatCompletionRequest request = ChatCompletionRequest.builder()
            .model(ChatCompletionModel.DEEPSEEK_CHAT)
            .addSystemMessage(systemPrompt)
            .addUserMessage(userPrompt)
            .responseFormat(ResponseFormatType.JSON_OBJECT)
            .build();

        ChatCompletionResponse completionResponse = deepSeekClient.chatCompletion(request).execute();

        return  completionResponse;
    }

    @SaIgnore
    @GetMapping(value = "/models", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelsResponse models() {
        return deepSeekClient.models();
    }


}

