package org.dromara.web.domain.bo;

import org.dromara.web.domain.Stock;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 股票信息业务对象 stock
 *
 * @author Yao Bo
 * @date 2025-03-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Stock.class, reverseConvertGenerate = false)
public class StockBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 股票名字
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 备注
     */
    private String remark;


}
