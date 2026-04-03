package org.dromara.web.domain;

import io.github.linpeilie.AutoMapperConfig__438;
import io.github.linpeilie.BaseMapper;
import org.dromara.web.domain.bo.StockBoToStockMapper;
import org.dromara.web.domain.vo.StockVo;
import org.dromara.web.domain.vo.StockVoToStockMapper;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__438.class,
    uses = {StockVoToStockMapper.class,StockBoToStockMapper.class},
    imports = {}
)
public interface StockToStockVoMapper extends BaseMapper<Stock, StockVo> {
}
