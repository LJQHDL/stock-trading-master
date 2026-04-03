package org.dromara.web.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.tool.GetStockDataBy7DaysTools;
import org.dromara.web.domain.bo.StockUsaBo;
import org.dromara.web.domain.vo.ApiResponseVo;
import org.dromara.web.domain.vo.PageResultVo;
import org.dromara.web.domain.vo.StockSymbolVo;
import org.dromara.web.domain.vo.StockUsaVo;
import org.dromara.web.service.MarketService;
import org.dromara.web.service.StockAnalysisAI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 美国股票数据
 *
 * @author ljq
 * @date 2025-05-15
 */
@SaIgnore
@RestController
@RequestMapping("/market")
@Slf4j
public class MarketController extends BaseController{

    @Autowired
    private MarketService stockUsaService;
//    LoginUser loginUser = LoginHelper.getLoginUser();
    @Autowired
    private GetStockDataBy7DaysTools getStockDataBy7DaysTools;
    @Autowired
    private  ChatLanguageModel chatLanguageModel;
    @Autowired
    private StockAnalysisAI stockAnalysisAI;

    /**
     * 查询美国股票数据列表
     */

    @GetMapping("/list")
    @Operation(summary = "查询美国股票数据列表")
    public TableDataInfo<StockUsaVo> list(StockUsaBo bo, PageQuery pageQuery) {
        return stockUsaService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询所有不重复的股票代码
     */
    @GetMapping("/symbols")
    @Operation(summary = "查询所有不重复的股票代码")
    public ApiResponseVo<List<StockSymbolVo>> getDistinctSymbols() {
        try {
            List<StockSymbolVo> symbols = stockUsaService.getDistinctSymbols();
            return ApiResponseVo.success(symbols);
        } catch (Exception e) {
            return ApiResponseVo.error("查询股票代码失败: " + e.getMessage());
        }
    }

    /**
     * 根据股票代码查询对应股票信息
     */
    @GetMapping("/symbol/{symbol}")
    @Operation(summary = "根据股票代码查询所有股票信息")
    public ApiResponseVo<List<StockUsaVo>> getStockBySymbol(
        @Parameter(description = "股票代码") @PathVariable String symbol) {
        try {
            List<StockUsaVo> stockInfoList = stockUsaService.queryBySymbol(symbol);
            if (stockInfoList != null && !stockInfoList.isEmpty()) {
                return ApiResponseVo.success(stockInfoList);
            } else {
                return ApiResponseVo.error("未找到股票代码为 " + symbol + " 的股票信息");
            }
        } catch (Exception e) {
            return ApiResponseVo.error("查询股票信息失败: " + e.getMessage());
        }
    }


    /**
     * 同步美国股票数据
     * 参考聚合数据API调用方式优化
     */
    @GetMapping("/sync")
    @Operation(summary = "同步美国股票数据")
    public ApiResponseVo<PageResultVo<StockUsaVo>> syncStockData(
        @Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer page) {
        try {
            System.out.println("开始同步第 " + page + " 页股票数据...");
            PageResultVo<StockUsaVo> result = stockUsaService.syncStockDataFromApi(page);
            System.out.println("第 " + page + " 页数据同步成功，共 " + result.getData().size() + " 条记录");
            return ApiResponseVo.success(result);
        } catch (Exception e) {
            System.err.println("第 " + page + " 页数据同步失败: " + e.getMessage());
            e.printStackTrace();
            return ApiResponseVo.error("同步数据失败: " + e.getMessage());
        }
    }

    /**
     * 多线程同步美国股票数据
     */
    @GetMapping("/sync/multi")
    @Operation(summary = "多线程同步美国股票数据")
    public ApiResponseVo<String> syncStockDataMultiThread(
        @Parameter(description = "起始页码") @RequestParam(defaultValue = "1") Integer startPage,
        @Parameter(description = "结束页码") @RequestParam(defaultValue = "5") Integer endPage,
        @Parameter(description = "线程池大小") @RequestParam(defaultValue = "3") Integer threadPoolSize) {

        if (startPage > endPage) {
            return ApiResponseVo.error("起始页码不能大于结束页码");
        }

        if (threadPoolSize <= 0 || threadPoolSize > 10) {
            return ApiResponseVo.error("线程池大小必须在1-10之间");
        }

        // 创建线程池 cpu核数 公式计算核数
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
//        Executors.newCachedThreadPool() 2n+1

        try {
            // 创建异步任务列表
            List<CompletableFuture<Void>> futures = new ArrayList<>();

            // 为每个页面创建异步任务
            IntStream.rangeClosed(startPage, endPage).forEach(page -> {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    try {
                        stockUsaService.syncStockDataFromApi(page);
                        System.out.println("页面 " + page + " 同步完成，线程: " + Thread.currentThread().getName());
                    } catch (Exception e) {
                        System.err.println("页面 " + page + " 同步失败: " + e.getMessage());
                    }
                }, executor);
                futures.add(future);
            });

            // 等待所有任务完成
            CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
            );

            // 设置超时时间（5分钟）
            allFutures.get(5, TimeUnit.MINUTES);

            return ApiResponseVo.success("多线程同步完成，共处理 " + (endPage - startPage + 1) + " 个页面");

        } catch (Exception e) {
            return ApiResponseVo.error("多线程同步失败: " + e.getMessage());
        } finally {
            // 关闭线程池
            executor.shutdown();
            try {
                if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 根据ids列表删除股票数据
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public ApiResponseVo<PageResultVo<StockUsaVo>> deleteById(@PathVariable Long[] ids){
        try{
            stockUsaService.deleteByIds(List.of(ids));
            return ApiResponseVo.deleteSuccess();
        }catch (Exception e){
            return ApiResponseVo.error("删除数据失败"+e.getMessage());
        }


    }
    /**
     * 获取股票信息详细信息
     * @param id 主键
     */
    @GetMapping("/{id}")
    public ApiResponseVo<StockUsaVo> getInfo(@NotNull(message = "主键不能为空")
                                             @PathVariable Long id) {
        StockUsaVo vo = stockUsaService.queryById(id);
        return ApiResponseVo.success(vo);
    }
    /**
     * 修改股票信息
     */

    @PutMapping
    public ApiResponseVo<String> edit(@Validated(EditGroup.class) @RequestBody StockUsaBo bo) {
        if (stockUsaService.updateByBo(bo)) {
            return ApiResponseVo.success("修改成功");
        }
        return ApiResponseVo.error("修改失败");
    }

    /**
     * 新增股票信息
     * @param bo
     * @return
     */
    @PostMapping
    public ApiResponseVo<String> add(@Validated(EditGroup.class) @RequestBody StockUsaBo bo) {
        HashSet<String> set = new HashSet<>();
        if (stockUsaService.insertByBo(bo)) {
            return ApiResponseVo.success("新增成功");
        }
        return ApiResponseVo.error("新增失败");
    }


    /**
     * 导出股票列表信息
     */
    @PostMapping("/export")
    public void export(StockUsaBo bo, HttpServletResponse response) {
        List<StockUsaVo> list = stockUsaService.queryList(bo);
        ExcelUtil.exportExcel(list, "股票列表信息", StockUsaVo.class, response);

    }

    @SaIgnore
    @GetMapping("/analyze/{symbol}")
    public String aichatmodel(@PathVariable String symbol){
        String stockData = getStockDataBy7DaysTools.getstockdataby7days(symbol);
        SystemMessage systemMsg = SystemMessage.from("\uD83D\uDCCC 角色设定\n" +
            "你是一个资深美股量化交易分析师，擅长从结构化数据中提取趋势、形态与交易信号。用户将传给你最近7个交易日的美国股票日线数据（按时间倒序排列，第0项为最新一天），每条数据包含：公司名称、行业、股票代码、开盘价、收盘价、最高价、最低价、涨跌幅、成交量、市值、更新时间等字段。" +
            "\uD83D\uDCCC 任务要求\n" +
            "\n" +
            "请基于这7天的数据，完成以下分析：\n" +
            "\n" +
            "1\uFE0F⃣ 数据趋势概览\n" +
            "计算7日累计涨跌幅。\n" +
            "描述价格走势（如：震荡上行、单边下跌、横盘整理）。\n" +
            "成交量变化趋势（放大/萎缩/平稳），是否与价格配合。\n" +
            "波动率观察（最高-最低价差、振幅变化）。\n" +
            "2\uFE0F⃣ 技术形态识别（基于K线组合）\n" +
            "是否出现经典形态（如：早晨之星、乌云盖顶、三连阳、孕线、突破前高/低等）？\n" +
            "是否存在支撑/阻力位？请结合价格高低点估算。\n" +
            "RSI 或 MACD 趋势（若无法计算指标，请基于价格动量描述强弱）。\n" +
            "3\uFE0F⃣ 行业与市值背景（可选加分项）\n" +
            "结合行业板块（category）与市值（mktcap），判断是否受宏观或行业轮动影响。\n" +
            "大盘股 vs 中小盘股在当前行情中的表现差异。\n" +
            "4\uFE0F⃣ 操作建议（分投资者类型）\n" +
            "\uD83D\uDD39 短线交易者（1-3天）：建议“买入”、“卖出”或“观望”，给出明确触发条件（如突破$XX或跌破$XX）。\n" +
            "\uD83D\uDFE1 中线持仓者（1-4周）：是否适合加仓/减仓？当前是否处于相对低位或高位？\n" +
            "⚠\uFE0F 风险提示：指出潜在风险（如：放量滞涨、利好兑现、行业利空、技术超买等）" +
            "\uD83D\uDCCC 输入格式说明\n" +
            "\n" +
            "你将收到一个包含7个 StockUsa 对象的列表（按时间倒序，索引0为最新交易日），例如：\n" +
            "\n" +
            "json\n" +
            "\n" +
            "\n[\n" +
            "  {\n" +
            "    \"cname\": \"Apple Inc.\",\n" +
            "    \"symbol\": \"AAPL\",\n" +
            "    \"price\": 192.34,\n" +
            "    \"open\": 190.12,\n" +
            "    \"high\": 193.55,\n" +
            "    \"low\": 189.88,\n" +
            "    \"chg\": 1.23,\n" +
            "    \"volume\": 45200000,\n" +
            "    \"mktcap\": 3000000000000,\n" +
            "    \"category\": \"科技\",\n" +
            "    \"updateTime\": \"2025-04-04\"\n" +
            "  },\n" +
            "  ... // 共7条，逐日倒序\n" +
            "]" +
            "\uD83D\uDCCC 输出格式要求\n" +
            "\n" +
            "请使用以下结构化格式输出，便于程序解析或前端展示：" +
            "# 美股分析报告：{symbol} - {cname}\n" +
            "\n" +
            "## \uD83D\uDCCA 1. 七日数据概览\n" +
            "- **累计涨跌幅**：+X.XX%\n" +
            "- **走势描述**：震荡上行，成交量温和放大\n" +
            "- **波动特征**：平均日振幅 X.X%，最大振幅出现在 X月X日\n" +
            "\n" +
            "## \uD83D\uDCC8 2. 技术形态与关键位\n" +
            "- **形态识别**：形成“三连阳+突破前高”看涨形态\n" +
            "- **支撑位**：≈ $XXX（X月X日低点）\n" +
            "- **阻力位**：≈ $XXX（历史高点/近期高点）\n" +
            "\n" +
            "## \uD83E\uDDED 3. 行业与市值背景\n" +
            "- 所属行业：{category} —— 近期行业整体 {上涨/承压}\n" +
            "- 市值规模：{mktcap} —— {大盘蓝筹/中盘成长}，流动性 {良好/一般}\n" +
            "\n" +
            "## \uD83D\uDCA1 4. 交易建议\n" +
            "- **短线（1-3天）**：  \n" +
            "  ✅ 建议在 $XXX 附近 **买入**，止损 $XXX，目标 $XXX  \n" +
            "  ❌ 若跌破 $XXX 则 **观望**\n" +
            "\n" +
            "- **中线（1-4周）**：  \n" +
            "  \uD83D\uDCCC 当前处于相对 {低位/高位}，{适合建仓/建议部分止盈}\n" +
            "\n" +
            "- **⚠\uFE0F 风险提示**：  \n" +
            "  - 成交量未持续放大，上涨持续性存疑  \n" +
            "  - 行业面临美联储利率政策不确定性\n" +
            "\uD83D\uDCCC 特别说明\n" +
            "\n" +
            "若数据不足7天、日期不连续、或字段缺失，请明确指出并说明分析局限性。\n" +
            "所有数值请保留两位小数，金额单位为美元（$），百分比带%符号。\n" +
            "避免模糊词汇如“可能”、“或许”，尽量给出明确判断与数值依据。");
        UserMessage userMsg = UserMessage.from(stockData);
        AiMessage aiMessage = chatLanguageModel.chat(systemMsg, userMsg).aiMessage();
        return aiMessage.text();
    }
    @SaIgnore
    @GetMapping("/AiChatModel/{symbol}")
    public String AiChatModel(@PathVariable String symbol){


          return   AiServices.builder(StockAnalysisAI.class)
        .chatLanguageModel(chatLanguageModel)
       .tools(getStockDataBy7DaysTools).build().analyzeStock(symbol);

    }

//    }
}
