package org.dromara.web.domain.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.web.domain.StockUsa;
import org.dromara.web.domain.vo.StockUsaVo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 美国股票数据业务对象 stock_usa
 *

 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AutoMappers({
    @AutoMapper(target = StockUsa.class, reverseConvertGenerate = false),
    @AutoMapper(target = StockUsaVo.class, reverseConvertGenerate = false)
})
public class StockUsaBo extends BaseEntity {

    /** 主键ID */
    @JsonProperty("id")
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

    private Date updateTime;
    /** 创建部门 */

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Long createDept;

    private Integer isDelete = 0;
    
    private Date currentLdate;
//    @JsonCreator
//    public StockUsaBo(@JsonProperty("id") String id) {
//        this.id = Long.parseLong(id); // 将字符串转换为Long
//    }
}
