package com.kiwi.cn.backend.common.service.api;

public class ResponseMessage {
    private final static String STATUS_OK = "0";
    private final static String STATUS_ERROR = "1";

    private String status;   // 状态
    private Object data;  // 返回值
    private Exception e;  // 异常类捕获
    private String msg;  // 自定义信息

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public Exception getE() {
        return e;
    }
    public void setE(Exception e) {
        this.e = e;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public ResponseMessage() {
        super();
    }
    public ResponseMessage(String status, Object data, Exception e, String msg) {
        super();
        this.status = status;
        this.data = data;
        this.e = e;
        this.msg = msg;
    }

    /**
     * 成功的结果
     * @param data 返回结果
     * @param msg 返回信息
     */
    public static ResponseMessage newOkInstance(Object data, String msg) {
        return new ResponseMessage(ResponseMessage.STATUS_OK, data, null, msg);
    }

    /**
     * 成功的结果
     * @param data  返回结果
     */
    public ResponseMessage newOkInstance(Object data) {
        return new ResponseMessage(ResponseMessage.STATUS_OK, data, null, null);
    }

    /**
     * 失败的结果
     * @param msg 返回信息
     */
    public static ResponseMessage newErrorInstance(String msg) {
        return new ResponseMessage(ResponseMessage.STATUS_ERROR, null, null, msg);
    }

    /**
     * 失败的结果
     * @param e    异常对象
     * @param msg 返回信息
     */
    public static ResponseMessage newErrorInstance(Exception e, String msg) {
        return new ResponseMessage(ResponseMessage.STATUS_ERROR, null, e, msg);
    }
}
