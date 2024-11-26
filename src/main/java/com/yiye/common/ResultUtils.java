package com.yiye.common;

/**
 * 返回工具类
 *
 * @author longyiye
 * @link <a href="https://github.com/longyiye/user-center-backend></a>
 */
public class ResultUtils {

    /**
     * 成功
     *
     * @param data T
     * @return BaseResponse<T>
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     *
     * @param errorCode ErrorCode
     * @return BaseResponse
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /**
     * 失败
     *
     * @param code        int
     * @param message     String
     * @param description String
     * @return BaseResponse
     */
    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse(code, null, message, description);
    }

    /**
     * 失败
     *
     * @param errorCode   ErrorCode
     * @param message     String
     * @param description String
     * @return BaseResponse
     */
    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse(errorCode.getCode(), null, message, description);
    }

    /**
     * 失败
     *
     * @param errorCode   ErrorCode
     * @param description String
     * @return BaseResponse
     */
    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse(errorCode.getCode(), errorCode.getMessage(), description);
    }
}