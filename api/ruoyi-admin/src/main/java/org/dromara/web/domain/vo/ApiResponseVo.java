package org.dromara.web.domain.vo;

import lombok.Data;

/**
 * API统一返回对象
 *
 * @author Your Name
 * @date 2023-03-22
 */
@Data
public class ApiResponseVo<T> {

    /** 返回状态码 */
    private Integer code;

    /** 返回消息 */
    private String reason;

    /** 返回数据 */
    private T result;

    /**
     * 成功返回
     *
     * @param data 数据
     * @return 响应结果
     */
    public static <T> ApiResponseVo<T> success(T data) {
        ApiResponseVo<T> response = new ApiResponseVo<>();
        response.setCode(0);
        response.setReason("SUCCESSED!");
        response.setResult(data);
        return response;
    }

    /**
     * 删除成功返回一个字符串
     */
    public static <T> ApiResponseVo<T> deleteSuccess() {
        ApiResponseVo<T> response = new ApiResponseVo<>();
        response.setCode(0);
        response.setReason("DELETED成功!");
        return response;
    }

    /**
     * 失败返回
     *
     * @param reason 失败原因
     * @return 响应结果
     */
    public static <T> ApiResponseVo<T> error(String reason) {
        ApiResponseVo<T> response = new ApiResponseVo<>();
        response.setCode(500);
        response.setReason(reason);
        return response;
    }
}
