package org.dromara.web.domain;

import javax.annotation.processing.Generated;
import org.dromara.web.domain.vo.StockVo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T00:25:42+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class StockToStockVoMapperImpl implements StockToStockVoMapper {

    @Override
    public StockVo convert(Stock arg0) {
        if ( arg0 == null ) {
            return null;
        }

        StockVo stockVo = new StockVo();

        stockVo.setCode( arg0.getCode() );
        stockVo.setId( arg0.getId() );
        stockVo.setName( arg0.getName() );
        stockVo.setRemark( arg0.getRemark() );

        return stockVo;
    }

    @Override
    public StockVo convert(Stock arg0, StockVo arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setCode( arg0.getCode() );
        arg1.setId( arg0.getId() );
        arg1.setName( arg0.getName() );
        arg1.setRemark( arg0.getRemark() );

        return arg1;
    }
}
