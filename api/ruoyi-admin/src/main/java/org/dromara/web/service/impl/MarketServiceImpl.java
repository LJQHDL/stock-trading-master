package org.dromara.web.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.web.domain.StockUsa;
import org.dromara.web.domain.bo.StockUsaBo;
import org.dromara.web.domain.vo.PageResultVo;
import org.dromara.web.domain.vo.StockSymbolVo;
import org.dromara.web.domain.vo.StockUsaVo;
import org.dromara.web.mapper.MarketMapper;
import org.dromara.web.service.MarketService;
import org.dromara.web.util.JuheApiUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private  MarketMapper baseMapper;

    @Value("${juhe.stock.api-key}")
    private String apiKey;

    @Value("${juhe.stock.url}")
    private String apiUrl;



    /**
     * 分页查询美国股票数据列表
     *
     * @param bo 查询条件
     * @param pageQuery 分页参数
     * @return 美国股票数据分页列表
     */
    @Override
    public TableDataInfo<StockUsaVo> queryPageList(StockUsaBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<StockUsa> lqw = buildQueryWrapper(bo);
        // 直接使用BaseMapperPlus的selectVoPage方法，自动完成DO到VO的转换
        Page<StockUsaVo> page = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(page);
    }



    /**
     * 构建查询包装器
     * 根据传入的业务对象参数构建一个LambdaQueryWrapper对象，用于后续的数据库查询操作
     * 此方法主要对StockUsa实体进行条件组装，以实现动态查询
     *
     * @param bo 业务对象(StockUsaBo)，包含从客户端接收到的查询参数
     * @return LambdaQueryWrapper<StockUsa> 查询包装器，用于执行与数据库的查询操作
     */
    private LambdaQueryWrapper<StockUsa> buildQueryWrapper(StockUsaBo bo) {
        // 获取业务对象中的参数
        Map<String, Object> params = bo.getParams();

        // 创建一个LambdaQueryWrapper对象，用于后续的条件组装
        LambdaQueryWrapper<StockUsa> lqw = Wrappers.lambdaQuery();

        // 对查询结果进行排序，按更新时间降序
        lqw.orderByDesc(StockUsa::getUpdateTime);

        // 根据公司名称模糊查询，如果传入的公司名称不为空
        lqw.like(StringUtils.isNotBlank(bo.getCname()), StockUsa::getCname, bo.getCname());

        // 根据股票代码精确查询，如果传入的股票代码不为空
        lqw.eq(StringUtils.isNotBlank(bo.getSymbol()), StockUsa::getSymbol, bo.getSymbol());

        // 根据类别精确查询，如果传入的类别不为空
        lqw.eq(StringUtils.isNotBlank(bo.getCategory()), StockUsa::getCategory, bo.getCategory());

        // 根据市场精确查询，如果传入的市场不为空
        lqw.eq(StringUtils.isNotBlank(bo.getMarket()), StockUsa::getMarket, bo.getMarket());

        // 添加日期范围查询支持
        if (params != null) {
            // 开始时间
            if (params.get("beginTime") != null) {
                lqw.ge(StockUsa::getCreateTime, params.get("beginTime"));
            }
            // 结束时间
            if (params.get("endTime") != null) {
                lqw.le(StockUsa::getCreateTime, params.get("endTime"));
            }
        }

        // 返回组装好的查询包装器
        return lqw;
    }

    /**
     * 新增美国股票数据
     *
     * @param bo 美国股票数据
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(StockUsaBo bo) {
        // 使用MapstructUtils进行转换
        StockUsa add = MapstructUtils.convert(bo, StockUsa.class);
        validEntityBeforeSave(add);
        if(add.getCurrentLdate()==null){
            add.setCurrentLdate(new Date());
        }
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改美国股票数据
     *
     * @param bo 美国股票数据
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(StockUsaBo bo) {
        // 使用MapstructUtils进行转换
        StockUsa update = MapstructUtils.convert(bo, StockUsa.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(StockUsa entity) {
        // 设置更新时间
        if (entity.getUpdateTime() == null) {
            entity.setUpdateTime(new Date());
        }
        if (entity.getCurrentLdate()==null){
            entity.setCurrentLdate(new Date());
        }
    }



    /**
     * 从API获取美国股票数据并保存
     * 参考聚合数据API调用方式进行优化
     *
     * @param page 页码
     * @return 分页结果
     */
    @Override
    public PageResultVo<StockUsaVo> syncStockDataFromApi(@NotNull Integer page) throws Exception {
        // 构建请求参数
        HashMap<String, String> params = new HashMap<>();
        params.put("key", apiKey);
        params.put("page", page.toString());

        // 方式一：使用工具类调用API（推荐）
        String apiResponse = JuheApiUtil.callUsaStockApi(apiKey, apiUrl, page.toString());
        
        // 方式二：直接调用（原有方式，保留作为备选）
        // URL url = new URL(String.format("%s?%s", apiUrl, formatParams(params)));
        // BufferedReader in = new BufferedReader(new InputStreamReader((url.openConnection()).getInputStream()));
        // String inputLine;
        // StringBuffer response = new StringBuffer();
        // while ((inputLine = in.readLine()) != null) {
        //     response.append(inputLine);
        // }
        // in.close();

        // 解析返回的JSON数据
        System.out.println("API响应: " + apiResponse);
        JSONObject jsonObject = JSON.parseObject(apiResponse);
        
        // 检查API响应状态
        if (jsonObject.getInteger("error_code") != 0) {
            throw new RuntimeException("API调用失败: " + jsonObject.getString("reason"));
        }

        // 获取结果数据
        JSONObject result = jsonObject.getJSONObject("result");
        PageResultVo<StockUsaVo> pageResult = new PageResultVo<>();
        pageResult.setTotalCount(result.getString("totalCount"));
        pageResult.setPage(result.getString("page"));
        pageResult.setNum(result.getString("num"));

        // 解析股票数据并保存
        List<StockUsaVo> stockList = new ArrayList<>();
        List<Map> dataList = result.getList("data", Map.class);
        for (Map<String, Object> item : dataList) {
            StockUsaBo stockBo = new StockUsaBo();
            stockBo.setCname(String.valueOf(item.get("cname")));
            stockBo.setCategory(String.valueOf(item.get("category")));
            stockBo.setSymbol(String.valueOf(item.get("symbol")));
            stockBo.setPrice(new BigDecimal(String.valueOf(item.get("price"))));
            stockBo.setDiff(new BigDecimal(String.valueOf(item.get("diff"))));
            stockBo.setChg(new BigDecimal(String.valueOf(item.get("chg"))));
            stockBo.setPreclose(new BigDecimal(String.valueOf(item.get("preclose"))));
            stockBo.setOpen(new BigDecimal(String.valueOf(item.get("open"))));
            stockBo.setHigh(new BigDecimal(String.valueOf(item.get("high"))));
            stockBo.setLow(new BigDecimal(String.valueOf(item.get("low"))));
//            stockBo.setCreateDept(Long.valueOf(String.valueOf(item.get("createDept"))));
//            stockBo.setCreateBy(Long.valueOf(String.valueOf(item.get("LJQ"))));
//            stockBo.setUpdateBy(Long.valueOf(String.valueOf(item.get("LJQ"))));
//            stockBo.setCreateTime(new Date(Long.valueOf(String.valueOf(item.get("createTime")))));
//            stockBo.setUpdateTime(new Date(Long.valueOf(String.valueOf(item.get("updateTime")))));

            // 处理振幅百分比
            String amplitude = String.valueOf(item.get("amplitude"));
            if (amplitude.endsWith("%")) {
                amplitude = amplitude.substring(0, amplitude.length() - 1);
            }
            stockBo.setAmplitude(new BigDecimal(amplitude));

            stockBo.setVolume(Long.valueOf(String.valueOf(item.get("volume"))));
            stockBo.setMktcap(new BigDecimal(String.valueOf(item.get("mktcap"))));
            stockBo.setMarket(String.valueOf(item.get("market")));
            stockBo.setUpdateTime(new Date());
            stockBo.setCurrentLdate(new Date());
            
            // 每天调取新数据直接插入数据库，不检查重复
            try {
                insertByBo(stockBo);
            } catch (Exception e) {
                System.err.println("插入股票数据失败: " + stockBo.getSymbol() + ", 错误: " + e.getMessage());
                // 继续处理下一条数据，不中断整个流程
                continue;
            }

            // 添加到返回列表
            StockUsaVo vo = MapstructUtils.convert(stockBo, StockUsaVo.class);
            stockList.add(vo);
        }


        pageResult.setData(stockList);
        return pageResult;
    }

    /**
     * 批量同步多个页面的股票数据
     *
     * @param startPage 起始页码
     * @param endPage 结束页码
     * @return 同步结果统计
     */
    @Override
    public String syncMultiplePages(Integer startPage, Integer endPage) throws Exception {
        int totalPages = endPage - startPage + 1;
        int successCount = 0;
        int failCount = 0;
        StringBuilder errorMessages = new StringBuilder();

        for (int page = startPage; page <= endPage; page++) {
            try {
                PageResultVo<StockUsaVo> result = syncStockDataFromApi(page);
                successCount++;
                System.out.println("页面 " + page + " 同步成功，共 " + result.getData().size() + " 条数据");
            } catch (Exception e) {
                failCount++;
                String errorMsg = "页面 " + page + " 同步失败: " + e.getMessage();
                errorMessages.append(errorMsg).append("; ");
                System.err.println(errorMsg);
            }
        }

        String resultMessage = String.format(
            "批量同步完成 - 总页面数: %d, 成功: %d, 失败: %d",
            totalPages, successCount, failCount
        );

        if (failCount > 0) {
            resultMessage += ". 失败详情: " + errorMessages.toString();
        }

        return resultMessage;
    }

    @Override
    public void deleteByIds(List<Long> ids) {
         baseMapper.deleteByIds(ids);
    }

    /**
     * 根据id查询具体股票信息
     * @param id
     * @return
     */

    @Override
    public StockUsaVo queryById(Long id) {
        // 直接使用BaseMapperPlus的selectVoById方法，自动完成DO到VO的转换
        return baseMapper.selectVoById(id);
    }

    @Override
    public List<StockUsaVo> queryList(StockUsaBo bo) {
        LambdaQueryWrapper<StockUsa> lqw = buildQueryWrapper(bo);
        // 直接使用BaseMapperPlus的selectVoList方法，自动完成DO到VO的转换
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询所有不重复的股票代码
     * @return 股票代码列表
     */
    @Override
    public List<StockSymbolVo> getDistinctSymbols() {
        List<String> symbols = baseMapper.selectDistinctSymbols();
        return symbols.stream()
                .map(StockSymbolVo::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockUsaVo> queryBySymbol(String symbol) {
        if (StringUtils.isBlank(symbol)) {
            return new ArrayList<>();
        }
        
        // 根据股票代码查询所有股票信息
        LambdaQueryWrapper<StockUsa> lqw = Wrappers.lambdaQuery();
        lqw.eq(StockUsa::getSymbol, symbol)
           .orderByDesc(StockUsa::getUpdateTime);
        
        List<StockUsa> stockUsaList = baseMapper.selectList(lqw);
        return MapstructUtils.convert(stockUsaList, StockUsaVo.class);
    }



    /**
     * 格式化请求参数
     * 参考聚合数据示例代码实现
     *
     * @param params 参数Map
     * @return 格式化后的参数字符串
     */
    private String formatParams(Map<String, String> params) {
        return params.entrySet().stream()
            .map(entry -> {
                try {
                    return entry.getKey() + "=" + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString());
                } catch (Exception e) {
                    System.err.println("参数编码失败: " + entry.getKey() + "=" + entry.getValue());
                    e.printStackTrace();
                    return entry.getKey() + "=" + entry.getValue();
                }
            })
            .collect(Collectors.joining("&"));
    }
}
