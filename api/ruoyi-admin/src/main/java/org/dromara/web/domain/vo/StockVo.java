package org.dromara.web.domain.vo;

import org.dromara.web.domain.Stock;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 股票信息视图对象 stock
 *
 * @author Yao Bo
 * @date 2025-03-22
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Stock.class)
public class StockVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 股票名字
     */
    @ExcelProperty(value = "股票名字")
    private String name;

    /**
     * 代码
     */
    @ExcelProperty(value = "代码")
    private String code;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
