package org.dromara.web.domain.bo;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.dromara.web.domain.StockUsa;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:03+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class StockUsaBoToStockUsaMapperImpl implements StockUsaBoToStockUsaMapper {

    @Override
    public StockUsa convert(StockUsaBo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        StockUsa stockUsa = new StockUsa();

        Map<String, Object> map = arg0.getParams();
        if ( map != null ) {
            stockUsa.setParams( new LinkedHashMap<String, Object>( map ) );
        }
        stockUsa.setSearchValue( arg0.getSearchValue() );
        stockUsa.setAmplitude( arg0.getAmplitude() );
        stockUsa.setCategory( arg0.getCategory() );
        stockUsa.setChg( arg0.getChg() );
        stockUsa.setCname( arg0.getCname() );
        stockUsa.setCreateBy( arg0.getCreateBy() );
        stockUsa.setCreateDept( arg0.getCreateDept() );
        stockUsa.setCreateTime( arg0.getCreateTime() );
        stockUsa.setCurrentLdate( arg0.getCurrentLdate() );
        stockUsa.setDiff( arg0.getDiff() );
        stockUsa.setHigh( arg0.getHigh() );
        stockUsa.setId( arg0.getId() );
        stockUsa.setIsDelete( arg0.getIsDelete() );
        stockUsa.setLow( arg0.getLow() );
        stockUsa.setMarket( arg0.getMarket() );
        stockUsa.setMktcap( arg0.getMktcap() );
        stockUsa.setOpen( arg0.getOpen() );
        stockUsa.setPreclose( arg0.getPreclose() );
        stockUsa.setPrice( arg0.getPrice() );
        stockUsa.setSymbol( arg0.getSymbol() );
        stockUsa.setUpdateBy( arg0.getUpdateBy() );
        stockUsa.setUpdateTime( arg0.getUpdateTime() );
        stockUsa.setVolume( arg0.getVolume() );

        return stockUsa;
    }

    @Override
    public StockUsa convert(StockUsaBo arg0, StockUsa arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

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
        arg1.setAmplitude( arg0.getAmplitude() );
        arg1.setCategory( arg0.getCategory() );
        arg1.setChg( arg0.getChg() );
        arg1.setCname( arg0.getCname() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateDept( arg0.getCreateDept() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setCurrentLdate( arg0.getCurrentLdate() );
        arg1.setDiff( arg0.getDiff() );
        arg1.setHigh( arg0.getHigh() );
        arg1.setId( arg0.getId() );
        arg1.setIsDelete( arg0.getIsDelete() );
        arg1.setLow( arg0.getLow() );
        arg1.setMarket( arg0.getMarket() );
        arg1.setMktcap( arg0.getMktcap() );
        arg1.setOpen( arg0.getOpen() );
        arg1.setPreclose( arg0.getPreclose() );
        arg1.setPrice( arg0.getPrice() );
        arg1.setSymbol( arg0.getSymbol() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        arg1.setVolume( arg0.getVolume() );

        return arg1;
    }
}
