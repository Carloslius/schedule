package plus.carlosliu.autoschedule.controller;

import lombok.Data;

@Data
public class BaseResponse {
    private Boolean flag;
    private Object data;
    private String msg;

    public BaseResponse() {
    }

    public BaseResponse(Boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public BaseResponse(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }
}
