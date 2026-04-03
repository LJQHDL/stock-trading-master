package org.dromara.web.domain.bo;

import io.github.linpeilie.AutoMapperConfig__438;
import io.github.linpeilie.BaseMapper;
import org.dromara.web.domain.Stock;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__438.class,
    uses = {},
    imports = {}
)
public interface StockBoToStockMapper extends BaseMapper<StockBo, Stock> {
}
