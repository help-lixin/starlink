package help.lixin.starlink.plugin.nacos.api.dto;


import java.io.Serializable;

public class ResponseResult<T> implements Serializable {
    private int code = 200;
    private String message;
    private T data;

    public static Builder newBuilder() {
        return new Builder<>();
    }

    public static ResponseResult success() {
        return new Builder().build();
    }

    public static ResponseResult success(Object data) {
        return newBuilder().data(data).build();
    }

    public static ResponseResult fail(String msg, int code) {
        return new Builder<>().msg(msg).code(code).build();
    }
    public static ResponseResult fail(String msg) {
        return new Builder<>().msg(msg).code(500).build();
    }


    public static class Builder<T> {
        private int code = 200;
        private String msg;
        private T data;

        public Builder<T> code(int code) {
            this.code = code;
            return this;
        }

        public Builder<T> msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResponseResult<T> build() {
            ResponseResult<T> response = new ResponseResult<>();
            response.setCode(code);
            response.setMessage(msg);
            response.setData(data);
            return response;
        }

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" + "code=" + code + ", msg='" + message + '\'' + ", data=" + data + '}';
    }
}