package help.lixin.starlink.plugin.harbor.api.dto.repository;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/17 下午4:18
 * @Description
 */
public class ExtraAttrsDTO {
    private String architecture;
    private String author;
    private String created;
    private String os;

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
