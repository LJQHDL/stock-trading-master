package org.dromara.web.domain.bo;

import io.github.linpeilie.AutoMapperConfig__434;
import io.github.linpeilie.BaseMapper;
import org.dromara.web.domain.StockUsa;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__434.class,
    uses = {StockUsaBoToStockUsaVoMapper.class},
    imports = {}
)
public interface StockUsaBoToStockUsaMapper extends BaseMapper<StockUsaBo, StockUsa> {
}
