package com.wanggs.vo;

import com.wanggs.enums.ResultEnum;
import lombok.Data;

/**
 * @Author: wgs
 * @Date: 2021/1/30
 * 返回数据体，JSON格式，根据不同的业务又不同的JSON体。
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResultVO success(T data){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultEnum.SUCCESS.getCode());
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
