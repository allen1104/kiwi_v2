package com.kiwi.cn.backend.common.service.impl;

import com.kiwi.cn.backend.constant.KiwiCommenConstants;

import java.io.Serializable;

public class ServiceResult<T, C> implements Serializable {

    private T data;
    private String message;
    private Integer status;

    ServiceResult(T data, Integer status, String message) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public static <D> ServiceResult<D, String> success() {
        return success(null);
    }

    public static <D> ServiceResult<D, String> success(D data) {
        ServiceResultBuilder<D, String> builder = builder();
        return builder.status(KiwiCommenConstants.SUCCESS).data(data).message("success").build();
    }


    public static <D, C> ServiceResult<D, C> error() {
        return error(null, null);
    }

    public static <D, C> ServiceResult<D, C> error(String msg, C errorCode) {
        ServiceResultBuilder<D, C> builder = builder();
        return builder.status(KiwiCommenConstants.FAIL).message(msg).code(errorCode).build();
    }

    static <D, C> ServiceResultBuilder<D, C> builder() {
        return new ServiceResultBuilder<>();
    }

    public static class ServiceResultBuilder<T, C> {

        private T data;
        private C code;
        private String message;
        private Integer status;

        ServiceResultBuilder() { //package private
        }

        public ServiceResultBuilder<T, C> data(T data) {
            this.data = data;
            return this;
        }

        ServiceResultBuilder<T, C> status(Integer status) {
            this.status = status;
            return this;
        }

        public ServiceResultBuilder<T, C> code(C code) {
            this.code = code;
            return this;
        }

        public ServiceResultBuilder<T, C> message(String message) {
            this.message = message;
            return this;
        }

        public ServiceResult<T, C> build() {
            return new ServiceResult<>(data, status, message);
        }
    }

    @Override
    public String toString() {
        return "ServiceResult{" +
                "data=" + data +
                ", message=" + message +
                ", status=" + status +
                '}';
    }
}