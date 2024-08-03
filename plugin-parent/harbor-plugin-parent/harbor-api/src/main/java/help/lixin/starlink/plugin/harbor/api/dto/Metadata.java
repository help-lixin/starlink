package help.lixin.starlink.plugin.harbor.api.dto;

public class Metadata {
    private String isPublic = "false";

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public void setPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public String getPublic() {
        return isPublic;
    }
}
