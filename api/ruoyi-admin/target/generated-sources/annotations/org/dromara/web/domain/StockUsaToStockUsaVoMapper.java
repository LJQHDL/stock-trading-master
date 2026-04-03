package org.dromara.web.domain;

import io.github.linpeilie.AutoMapperConfig__434;
import io.github.linpeilie.BaseMapper;
import org.dromara.web.domain.bo.StockUsaBoToStockUsaMapper;
import org.dromara.web.domain.bo.StockUsaBoToStockUsaVoMapper;
import org.dromara.web.domain.vo.StockUsaVo;
import org.dromara.web.domain.vo.StockUsaVoToStockUsaMapper;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__434.class,
    uses = {StockUsaBoToStockUsaVoMapper.class,StockUsaVoToStockUsaMapper.class,StockUsaBoToStockUsaMapper.class},
    imports = {}
)
public interface StockUsaToStockUsaVoMapper extends BaseMapper<StockUsa, StockUsaVo> {
}
