package help.lixin.eureka.api.exception;

public class EurekaClientException extends RuntimeException {

    private int code;

    public EurekaClientException(int code) {
        this.code = code;
    }

    public EurekaClientException(String message, int code) {
        super(message);
        this.code = code;
    }

    public EurekaClientException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public EurekaClientException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
