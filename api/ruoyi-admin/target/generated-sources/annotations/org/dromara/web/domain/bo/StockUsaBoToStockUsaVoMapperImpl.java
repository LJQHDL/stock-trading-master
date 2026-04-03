package org.dromara.web.domain.bo;

import javax.annotation.processing.Generated;
import org.dromara.web.domain.vo.StockUsaVo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T00:25:40+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class StockUsaBoToStockUsaVoMapperImpl implements StockUsaBoToStockUsaVoMapper {

    @Override
    public StockUsaVo convert(StockUsaBo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        StockUsaVo stockUsaVo = new StockUsaVo();

        stockUsaVo.setAmplitude( arg0.getAmplitude() );
        stockUsaVo.setCategory( arg0.getCategory() );
        stockUsaVo.setChg( arg0.getChg() );
        stockUsaVo.setCname( arg0.getCname() );
        stockUsaVo.setCreateBy( arg0.getCreateBy() );
        stockUsaVo.setCreateDept( arg0.getCreateDept() );
        stockUsaVo.setCreateTime( arg0.getCreateTime() );
        stockUsaVo.setCurrentLdate( arg0.getCurrentLdate() );
        stockUsaVo.setDiff( arg0.getDiff() );
        stockUsaVo.setHigh( arg0.getHigh() );
        stockUsaVo.setId( arg0.getId() );
        stockUsaVo.setIsDelete( arg0.getIsDelete() );
        stockUsaVo.setLow( arg0.getLow() );
        stockUsaVo.setMarket( arg0.getMarket() );
        stockUsaVo.setMktcap( arg0.getMktcap() );
        stockUsaVo.setOpen( arg0.getOpen() );
        stockUsaVo.setPreclose( arg0.getPreclose() );
        stockUsaVo.setPrice( arg0.getPrice() );
        stockUsaVo.setSymbol( arg0.getSymbol() );
        stockUsaVo.setUpdateBy( arg0.getUpdateBy() );
        stockUsaVo.setUpdateTime( arg0.getUpdateTime() );
        stockUsaVo.setVolume( arg0.getVolume() );

        return stockUsaVo;
    }

    @Override
    public StockUsaVo convert(StockUsaBo arg0, StockUsaVo arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

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
