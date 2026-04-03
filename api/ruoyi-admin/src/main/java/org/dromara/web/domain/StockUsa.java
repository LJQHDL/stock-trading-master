package org.dromara.web.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 美国股票数据对象 stock_usa
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("stock_usa")
public class StockUsa extends BaseEntity {

    /** 主键ID */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 公司名称 */
    private String cname;

    /** 行业板块 */
    private String category;

    /** 股票代码 */
    private String symbol;

    /** 最新价 */
    private BigDecimal price;

    /** 涨跌额 */
    private BigDecimal diff;

    /** 涨跌幅(%) */
    private BigDecimal chg;

    /** 昨收价 */
    private BigDecimal preclose;

    /** 今开价 */
    private BigDecimal open;

    /** 最高价 */
    private BigDecimal high;

    /** 最低价 */
    private BigDecimal low;

    /** 振幅(%) */
    private BigDecimal amplitude;

    /** 成交量 */
    private Long volume;

    /** 市值 */
    private BigDecimal mktcap;

    /** 上市地 */
    private String market;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**创建者**/
    @TableField( fill = FieldFill.INSERT)
    private Long createDept;
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    @TableField("is_delete")
    @TableLogic
    private Integer isDelete=0;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date currentLdate;

}
