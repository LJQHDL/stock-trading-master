package org.dromara.config;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.dromara.tool.GetStockDataBy7DaysTools;
import org.dromara.web.service.StockAnalysisAI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * StockAnalysisAI 配置类
 * 配置AI服务与工具的集成
 *
 * @author AI Assistant
 */
@Configuration
public class StockAnalysisAIConfiguration {

    /**
     * 配置StockAnalysisAI服务，集成GetStockDataBy7DaysTools工具
     *
     * @param chatLanguageModel LangChain4j聊天模型
     * @param getStockDataBy7DaysTools 股票数据获取工具
     * @return 配置好的StockAnalysisAI服务实例
     */
    @Bean
    @Primary
    public StockAnalysisAI stockAnalysisAI(ChatLanguageModel chatLanguageModel,
                                          GetStockDataBy7DaysTools getStockDataBy7DaysTools) {
        return AiServices.builder(StockAnalysisAI.class)
                .chatLanguageModel(chatLanguageModel)
                .tools(getStockDataBy7DaysTools)
                .build();
    }
}