package org.dromara.web.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 分页结果对象
 *
 * @author Your Name
 * @date 2023-03-22
 */
@Data
public class PageResultVo<T> {

    /** 总记录数 */
    private String totalCount;

    /** 当前页码 */
    private String page;

    /** 每页显示条数 */
    private String num;

    /** 数据列表 */
    private List<T> data;
}
