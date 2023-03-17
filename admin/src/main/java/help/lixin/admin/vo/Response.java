package help.lixin.admin.vo;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private int code = 200;
    private String msg;
    private T data;

    public static Builder newBuilder() {
        return new Builder<>();
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

        public Response<T> build() {
            Response<T> response = new Response<>();
            response.setCode(code);
            response.setMsg(msg);
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }
}
