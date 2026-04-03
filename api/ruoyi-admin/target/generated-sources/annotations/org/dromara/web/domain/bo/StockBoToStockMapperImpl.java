package org.dromara.web.domain.bo;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.dromara.web.domain.Stock;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:03+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class StockBoToStockMapperImpl implements StockBoToStockMapper {

    @Override
    public Stock convert(StockBo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Stock stock = new Stock();

        stock.setCreateBy( arg0.getCreateBy() );
        stock.setCreateDept( arg0.getCreateDept() );
        stock.setCreateTime( arg0.getCreateTime() );
        Map<String, Object> map = arg0.getParams();
        if ( map != null ) {
            stock.setParams( new LinkedHashMap<String, Object>( map ) );
        }
        stock.setSearchValue( arg0.getSearchValue() );
        stock.setUpdateBy( arg0.getUpdateBy() );
        stock.setUpdateTime( arg0.getUpdateTime() );
        stock.setCode( arg0.getCode() );
        stock.setId( arg0.getId() );
        stock.setName( arg0.getName() );
        stock.setRemark( arg0.getRemark() );

        return stock;
    }

    @Override
    public Stock convert(StockBo arg0, Stock arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateDept( arg0.getCreateDept() );
        arg1.setCreateTime( arg0.getCreateTime() );
        if ( arg1.getParams() != null ) {
            Map<String, Object> map = arg0.getParams();
            if ( map != null ) {
                arg1.getParams().clear();
                arg1.getParams().putAll( map );
            }
            else {
                arg1.setParams( null );
            }
        }
        else {
            Map<String, Object> map = arg0.getParams();
            if ( map != null ) {
                arg1.setParams( new LinkedHashMap<String, Object>( map ) );
            }
        }
        arg1.setSearchValue( arg0.getSearchValue() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        arg1.setCode( arg0.getCode() );
        arg1.setId( arg0.getId() );
        arg1.setName( arg0.getName() );
        arg1.setRemark( arg0.getRemark() );

        return arg1;
    }
}
