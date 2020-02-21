package nao;

import java.util.List;

public class CommonResponse {
    private int code;
    private String message;
    private Boolean success;
    private List<LocationInfoMsg> data;

    public CommonResponse() {
    }
    public CommonResponse ( List<LocationInfoMsg> data){
        this.data=data;
        this.code=200;
        this.message="success";
        this.success=true;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int var1) {
        this.code = var1;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String var1) {
        this.message = var1;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean var1) {
        this.success = var1;
    }

    public List<LocationInfoMsg> getData() {
        return data;
    }

    public void setData(List<LocationInfoMsg> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", bizData='" + data + '\'' +
                '}';
    }
}