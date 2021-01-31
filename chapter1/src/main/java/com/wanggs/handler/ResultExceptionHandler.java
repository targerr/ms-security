package com.wanggs.handler;

import com.wanggs.exception.ResultException;
import com.wanggs.vo.ResultVO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: wgs
 * @Date: 2021/1/31
 */
@ControllerAdvice
public class ResultExceptionHandler {
    @ExceptionHandler(value = ResultException.class)
    @ResponseBody
    public ResultVO handlerExceptionHandler(ResultException e) {
        return ResultVO.error(e.getCode(), e.getMessage());
    }
}
