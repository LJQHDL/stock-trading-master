package org.dromara.web.domain.vo;

import javax.annotation.processing.Generated;
import org.dromara.web.domain.Stock;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:03+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class StockVoToStockMapperImpl implements StockVoToStockMapper {

    @Override
    public Stock convert(StockVo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Stock stock = new Stock();

        stock.setCode( arg0.getCode() );
        stock.setId( arg0.getId() );
        stock.setName( arg0.getName() );
        stock.setRemark( arg0.getRemark() );

        return stock;
    }

    @Override
    public Stock convert(StockVo arg0, Stock arg1) {
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
