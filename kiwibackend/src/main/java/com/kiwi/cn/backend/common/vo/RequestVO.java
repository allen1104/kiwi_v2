package com.kiwi.cn.backend.common.vo;

import lombok.Data;

/**
 * @author Allen
 */
@Data
public class RequestVO<T>{
    private PageVO pageVO;
    private T searchParams;
}
