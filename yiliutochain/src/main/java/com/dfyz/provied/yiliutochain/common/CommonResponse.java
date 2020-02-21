package com.dfyz.provied.yiliutochain.common;

public class CommonResponse {
    private  int code;
    private  boolean success;
    private  String message;

    public CommonResponse (){

        this.message="success";
        this.success=true;
        this.code=200;
    }

    public CommonResponse (int code,Boolean success,String message){
        super();
        this.message=message;
        this.success=success;
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
