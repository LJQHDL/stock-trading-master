package org.dromara.web.domain.bo;

import io.github.linpeilie.AutoMapperConfig__434;
import io.github.linpeilie.BaseMapper;
import org.dromara.web.domain.vo.StockUsaVo;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__434.class,
    uses = {StockUsaBoToStockUsaMapper.class},
    imports = {}
)
public interface StockUsaBoToStockUsaVoMapper extends BaseMapper<StockUsaBo, StockUsaVo> {
}
