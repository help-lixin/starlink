package help.lixin.starlink.plugin.jsch.api.dto;

public class CommandExecuteResult {
    // 是否执行成功
    private Boolean isSuccess;
    private String msg;

    public Boolean isSuccess() {
        return isSuccess;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
