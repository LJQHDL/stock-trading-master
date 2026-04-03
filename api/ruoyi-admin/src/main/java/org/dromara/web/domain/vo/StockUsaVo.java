package org.dromara.web.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.web.domain.StockUsa;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 美国股票数据视图对象 stock_usa
 *
 * @author Your Name
 * @date 2023-03-22
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = StockUsa.class)
public class StockUsaVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ExcelProperty(value = "ID")
    private Long id;

    /** 公司名称 */
    @ExcelProperty(value = "公司名称")
    private String cname;

    /** 行业板块 */
    @ExcelProperty(value = "行业板块")
    private String category;

    /** 股票代码 */
    @ExcelProperty(value = "股票代码")
    private String symbol;

    /** 最新价 */
    @ExcelProperty(value = "最新价")
    private BigDecimal price;

    /** 涨跌额 */
    @ExcelProperty(value = "涨跌额")
    private BigDecimal diff;

    /** 涨跌幅(%) */
    @ExcelProperty(value = "涨跌幅(%)")
    private BigDecimal chg;

    /** 昨收价 */
    @ExcelProperty(value = "昨收价")
    private BigDecimal preclose;

    /** 今开价 */
    @ExcelProperty(value = "今开价")
    private BigDecimal open;

    /** 最高价 */
    @ExcelProperty(value = "最高价")
    private BigDecimal high;

    /** 最低价 */
    @ExcelProperty(value = "最低价")
    private BigDecimal low;

    /** 振幅(%) */
    @ExcelProperty(value = "振幅(%)")
    private BigDecimal amplitude;

    /** 成交量 */
    @ExcelProperty(value = "成交量")
    private Long volume;

    /** 市值 */
    @ExcelProperty(value = "市值")
    private BigDecimal mktcap;

    /** 上市地 */
    @ExcelProperty(value = "上市地")
    private String market;

    /** 更新时间 */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    @ExcelProperty(value = "创建部门")
    private Long createDept;

    @ExcelProperty(value = "创建者")

    private Long createBy;
    @ExcelProperty(value = "创建时间")
    private Date createTime;
    @ExcelProperty(value = "更新者")
    private Long updateBy;

    @ExcelProperty(value = "删除标志")
    private Integer isDelete = 0;

    @ExcelProperty(value = "当前时间")
    private Date currentLdate;

}
