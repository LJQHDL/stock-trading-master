package org.dromara.web.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.web.domain.bo.StockUsaBo;
import org.dromara.web.domain.vo.PageResultVo;
import org.dromara.web.domain.vo.StockSymbolVo;
import org.dromara.web.domain.vo.StockUsaVo;

import java.util.List;

/**
 * 美国股票数据Service接口
 *
 * @author Your Name
 * @date 2023-03-22
 */
public interface MarketService {



    /**
     * 分页查询美国股票数据列表
     *
     * @param bo 查询条件
     * @param pageQuery 分页参数
     * @return 美国股票数据分页列表
     */
    TableDataInfo<StockUsaVo> queryPageList(StockUsaBo bo, PageQuery pageQuery);


    /**
     * 新增美国股票数据
     *
     * @param bo 美国股票数据
     * @return 是否新增成功
     */
    Boolean insertByBo(StockUsaBo bo);

    /**
     * 修改美国股票数据
     *
     * @param bo 美国股票数据
     * @return 是否修改成功
     */
    Boolean updateByBo(StockUsaBo bo);



    /**
     * 从API获取美国股票数据并保存
     *
     * @param page 页码
     * @return 分页结果
     */
    PageResultVo<StockUsaVo> syncStockDataFromApi(Integer page) throws Exception;

    /**
     * 批量同步多个页面的股票数据
     *
     * @param startPage 起始页码
     * @param endPage 结束页码
     * @return 同步结果统计
     */
    String syncMultiplePages(Integer startPage, Integer endPage) throws Exception;

    void deleteByIds(List<Long> ids);

    StockUsaVo queryById(Long id);

    List<StockUsaVo> queryList(StockUsaBo bo);

    /**
     * 查询所有不重复的股票代码
     * @return 股票代码列表
     */
    List<StockSymbolVo> getDistinctSymbols();

    /**
     * 根据股票代码查询所有股票信息
     *
     * @param symbol 股票代码
     * @return 股票信息列表
     */
    List<StockUsaVo> queryBySymbol(String symbol);
}
