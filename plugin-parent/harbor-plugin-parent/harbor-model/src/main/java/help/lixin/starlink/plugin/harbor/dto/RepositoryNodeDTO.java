package help.lixin.starlink.plugin.harbor.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/17 下午7:05
 * @Description
 */
public class RepositoryNodeDTO {

    private Long parentId = 0l;

    // ======== 仓库目录相关 ========
    private String id;

    private String name;

    private Date createTime;

    // ======== 镜像相关 ========
    private String digest;

    private String tag;

    private String size;

    private String pushTime;

    private String pullTime;

    private List<RepositoryNodeDTO> children = new ArrayList<>();

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public String getPullTime() {
        return pullTime;
    }

    public void setPullTime(String pullTime) {
        this.pullTime = pullTime;
    }

    public List<RepositoryNodeDTO> getChildren() {
        return children;
    }

    public void setChildren(List<RepositoryNodeDTO> children) {
        this.children = children;
    }
}
