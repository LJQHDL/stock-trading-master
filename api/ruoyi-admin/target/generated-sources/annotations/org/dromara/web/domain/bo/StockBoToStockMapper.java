package org.dromara.web.domain.bo;

import io.github.linpeilie.AutoMapperConfig__434;
import io.github.linpeilie.BaseMapper;
import org.dromara.web.domain.Stock;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__434.class,
    uses = {},
    imports = {}
)
public interface StockBoToStockMapper extends BaseMapper<StockBo, Stock> {
}
