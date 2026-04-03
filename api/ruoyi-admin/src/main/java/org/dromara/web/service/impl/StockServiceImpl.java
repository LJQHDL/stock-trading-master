package org.dromara.web.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.web.domain.bo.StockBo;
import org.dromara.web.domain.vo.StockVo;
import org.dromara.web.domain.Stock;
import org.dromara.web.mapper.StockMapper;
import org.dromara.web.service.IStockService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 股票信息Service业务层处理
 *
 * @author Yao Bo
 * @date 2025-03-22
 */
@RequiredArgsConstructor
@Service
public class StockServiceImpl implements IStockService {

    private final StockMapper baseMapper;

    /**
     * 查询股票信息
     *
     * @param id 主键
     * @return 股票信息
     */
    @Override
    public StockVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询股票信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 股票信息分页列表
     */
    @Override
    public TableDataInfo<StockVo> queryPageList(StockBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Stock> lqw = buildQueryWrapper(bo);
        Page<StockVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的股票信息列表
     *
     * @param bo 查询条件
     * @return 股票信息列表
     */
    @Override
    public List<StockVo> queryList(StockBo bo) {
        LambdaQueryWrapper<Stock> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Stock> buildQueryWrapper(StockBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Stock> lqw = Wrappers.lambdaQuery();
        lqw.orderByAsc(Stock::getId);
        lqw.like(StringUtils.isNotBlank(bo.getName()), Stock::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), Stock::getCode, bo.getCode());
        return lqw;
    }

    /**
     * 新增股票信息
     *
     * @param bo 股票信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(StockBo bo) {
        Stock add = MapstructUtils.convert(bo, Stock.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改股票信息
     *
     * @param bo 股票信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(StockBo bo) {
        Stock update = MapstructUtils.convert(bo, Stock.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Stock entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除股票信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
