package com.wanggs.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @Author: wgs
 * @Date: 2021/1/30
 */
@Getter
public enum ResultEnum {
    /**成功状态码*/
    SUCCESS(0, "成功"),
    /*业务错误码*/
    PARAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),

    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),

    ORDER_NOT_EXIST(12, "订单不存在"),

    CART_EMPTY(18, "购物车为空"),

    ORDER_OWNER_ERROR(19, "该订单不属于给用户"),

    ORDER_STATUS_ERROR(20, "订单状态不正确"),

    ORDER_UPDATE_ERROR(21, "订单更新失败"),

    PRODUCT_UPDATE_ERROR(22, "更新商品失败"),

    PRODUCT_STATUS_ERROR(24, "商品状态不正确"),

    ORDER_CANCEL_SUCCESS(25,"成功取消订单"),

    ORDER_FINISH_SUCCESS(26,"成功完结订单"),
    LOGIN_FAIL(27,"登陆失败"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
