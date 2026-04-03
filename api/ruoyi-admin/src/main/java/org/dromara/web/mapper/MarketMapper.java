package org.dromara.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.web.domain.StockUsa;
import org.dromara.web.domain.vo.StockUsaVo;

import java.util.List;

@Mapper
public interface MarketMapper extends BaseMapperPlus<StockUsa, StockUsaVo> {

    /**
     * 查询所有不重复的股票代码
     * @return 股票代码列表
     */
    @Select("SELECT DISTINCT symbol FROM stock_usa WHERE is_delete = 0 ORDER BY symbol")
    List<String> selectDistinctSymbols();

}
