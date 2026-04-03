package org.dromara.web.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 股票信息对象 stock
 *
 * @author Yao Bo
 * @date 2025-03-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("stock")
public class Stock extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id" ,type= IdType.AUTO)
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
