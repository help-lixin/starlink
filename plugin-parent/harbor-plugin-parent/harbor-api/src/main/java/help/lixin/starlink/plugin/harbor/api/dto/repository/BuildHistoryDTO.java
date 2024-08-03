package help.lixin.starlink.plugin.harbor.api.dto.repository;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/17 下午4:18
 * @Description
 */
public class BuildHistoryDTO {
    private boolean absolute;
    private String href;

    public boolean isAbsolute() {
        return absolute;
    }

    public void setAbsolute(boolean absolute) {
        this.absolute = absolute;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
