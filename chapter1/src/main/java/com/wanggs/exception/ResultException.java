package com.wanggs.exception;

import com.wanggs.enums.ResultEnum;
import lombok.Getter;

/**
 * @Author: wgs
 * @Date: 2021/1/30
 */
@Getter
public class ResultException extends RuntimeException{
    private Integer code ;
    public ResultException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public ResultException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}
