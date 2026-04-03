package org.dromara.web.domain.vo;

import io.github.linpeilie.AutoMapperConfig__438;
import io.github.linpeilie.BaseMapper;
import org.dromara.web.domain.StockUsa;
import org.dromara.web.domain.StockUsaToStockUsaVoMapper;
import org.dromara.web.domain.bo.StockUsaBoToStockUsaVoMapper;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__438.class,
    uses = {StockUsaBoToStockUsaVoMapper.class,StockUsaToStockUsaVoMapper.class},
    imports = {}
)
public interface StockUsaVoToStockUsaMapper extends BaseMapper<StockUsaVo, StockUsa> {
}
