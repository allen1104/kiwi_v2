package com.kiwi.cn.backend.common.service.impl;

import com.kiwi.cn.backend.common.service.CodeMessage;

enum CodeMessageEnum implements CodeMessage<Integer> {
    some_error(1001, "错误信息。");
    Integer code;
    String message;

    CodeMessageEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}