package org.dromara.web.domain.vo;

import io.github.linpeilie.AutoMapperConfig__434;
import io.github.linpeilie.BaseMapper;
import org.dromara.web.domain.Stock;
import org.dromara.web.domain.StockToStockVoMapper;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__434.class,
    uses = {StockToStockVoMapper.class},
    imports = {}
)
public interface StockVoToStockMapper extends BaseMapper<StockVo, Stock> {
}
