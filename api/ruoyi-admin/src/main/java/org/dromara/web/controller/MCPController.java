package org.dromara.web.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.McpTransport;
import dev.langchain4j.mcp.client.transport.http.HttpMcpTransport;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.tool.ToolProvider;
import lombok.RequiredArgsConstructor;
import org.dromara.agent.Bot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;

@SaIgnore
@RestController
@RequiredArgsConstructor
public class MCPController {
    private final ChatLanguageModel chatLanguageModel;

    @GetMapping("/github")
    public String lowChat() throws Exception {
        McpTransport transport = new HttpMcpTransport.Builder()
            .sseUrl("http://localhost:3001/sse")
            .timeout(Duration.ofSeconds(60))
            .logRequests(true)
            .logResponses(true)
            .build();

        McpClient mcpClient = new DefaultMcpClient.Builder()
            .transport(transport)
            .build();

        ToolProvider toolProvider = McpToolProvider.builder()
            .mcpClients(List.of(mcpClient))
            .build();

        Bot bot = AiServices.builder(Bot.class)
            .chatLanguageModel(chatLanguageModel)
            .toolProvider(toolProvider)
            .build();
        String response = null;
        try {
             response = bot.chat("What is 5+12? Use the provided tool to answer " +
                "and always assume that the tool is correct.");
            System.out.println("RESPONSE: " + response);
        } finally {
            mcpClient.close();
        }
        return response;
    }



}

