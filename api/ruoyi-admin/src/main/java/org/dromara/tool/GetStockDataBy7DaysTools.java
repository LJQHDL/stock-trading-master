package org.dromara.tool;

import dev.langchain4j.agent.tool.Tool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.web.domain.vo.StockUsaVo;
import org.dromara.web.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetStockDataBy7DaysTools{

    @Autowired
    private MarketService marketService;

    @Tool("根据股票代码获取最近7天的股票数据，用于分析股票趋势")
    public String getstockdataby7days(String symbol){
        List<StockUsaVo>  stcokDataList = marketService.queryBySymbol(symbol.toUpperCase());
        List<StockUsaVo>  stockDataRecent7Days = stcokDataList.stream()
            .limit(7)
            .collect(Collectors.toList());
        // 格式化数据为AI可分析的文本
        StringBuilder analysis = new StringBuilder();
        analysis.append(String.format("股票代码: %s (%s)\n", symbol, stockDataRecent7Days.get(0).getCname()));
        analysis.append(String.format("行业板块: %s\n", stockDataRecent7Days.get(0).getCategory()));
        analysis.append(String.format("上市地: %s\n\n", stockDataRecent7Days.get(0).getMarket()));

        analysis.append("=== 最近7天股票数据 ===\n");

        for (int i = 0; i < stockDataRecent7Days.size(); i++) {
            StockUsaVo stock = stockDataRecent7Days.get(i);
            analysis.append(String.format("第%d天 (%s):\n", i + 1,
                stock.getUpdateTime() != null ?
                    stock.getUpdateTime().toString() : "未知时间"));
            analysis.append(String.format("  最新价: $%.2f\n", stock.getPrice()));
            analysis.append(String.format("  涨跌额: $%.2f\n", stock.getDiff()));
            analysis.append(String.format("  涨跌幅: %.2f%%\n", stock.getChg()));
            analysis.append(String.format("  开盘价: $%.2f\n", stock.getOpen()));
            analysis.append(String.format("  最高价: $%.2f\n", stock.getHigh()));
            analysis.append(String.format("  最低价: $%.2f\n", stock.getLow()));
            analysis.append(String.format("  成交量: %,d\n", stock.getVolume()));
            analysis.append(String.format("  市值: $%.2f\n", stock.getMktcap()));
            analysis.append("\n");
        }
        // 添加分析提示
        analysis.append("=== 分析建议 ===\n");
        analysis.append("请基于以上数据分析：\n");
        analysis.append("1. 股价趋势（上涨/下跌/震荡）\n");
        analysis.append("2. 价格波动幅度\n");
        analysis.append("3. 成交量变化\n");
        analysis.append("4. 技术指标分析\n");
        analysis.append("5. 投资建议（仅供参考）\n");

        return analysis.toString();


    }


}
