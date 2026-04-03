package org.dromara.web.service;

import org.dromara.web.domain.vo.StockVo;
import org.dromara.web.domain.bo.StockBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 股票信息Service接口
 *
 * @author Yao Bo
 * @date 2025-03-22
 */
public interface IStockService {

    /**
     * 查询股票信息
     *
     * @param id 主键
     * @return 股票信息
     */
    StockVo queryById(Long id);

    /**
     * 分页查询股票信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 股票信息分页列表
     */
    TableDataInfo<StockVo> queryPageList(StockBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的股票信息列表
     *
     * @param bo 查询条件
     * @return 股票信息列表
     */
    List<StockVo> queryList(StockBo bo);

    /**
     * 新增股票信息
     *
     * @param bo 股票信息
     * @return 是否新增成功
     */
    Boolean insertByBo(StockBo bo);

    /**
     * 修改股票信息
     *
     * @param bo 股票信息
     * @return 是否修改成功
     */
    Boolean updateByBo(StockBo bo);

    /**
     * 校验并批量删除股票信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
