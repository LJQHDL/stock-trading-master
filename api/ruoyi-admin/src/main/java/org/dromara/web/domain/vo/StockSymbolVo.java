package org.dromara.web.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 股票代码视图对象
 *
 * @author System
 * @date 2025-01-20
 */
@Data
@ExcelIgnoreUnannotated
public class StockSymbolVo {

    /** 股票代码 */
    @ExcelProperty(value = "股票代码")
    private String symbol;

    public StockSymbolVo() {}

    public StockSymbolVo(String symbol) {
        this.symbol = symbol;
    }
}