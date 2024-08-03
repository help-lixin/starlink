package help.lixin.starlink.plugin.harbor.api.dto.repository;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/17 下午4:16
 * @Description
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArtifactDTO {

    @JsonProperty("addition_links")
    private AdditionLinksDTO additionLinks;
    private String digest;
    @JsonProperty("extra_attrs")
    private ExtraAttrsDTO extraAttrs;
    private String icon;
    private Integer id;
    private String labels;
    @JsonProperty("manifest_media_type")
    private String manifestMediaType;
    @JsonProperty("media_type")
    private String mediaType;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("pull_time")
    private String pullTime;
    @JsonProperty("push_time")
    private String pushTime;
    private String references;
    @JsonProperty("repository_id")
    private Integer repositoryId;
    private Integer size;
    private List<TagDTO> tags;
    private String type;

    public AdditionLinksDTO getAdditionLinks() {
        return additionLinks;
    }

    public void setAdditionLinks(AdditionLinksDTO additionLinks) {
        this.additionLinks = additionLinks;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public ExtraAttrsDTO getExtraAttrs() {
        return extraAttrs;
    }

    public void setExtraAttrs(ExtraAttrsDTO extraAttrs) {
        this.extraAttrs = extraAttrs;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getManifestMediaType() {
        return manifestMediaType;
    }

    public void setManifestMediaType(String manifestMediaType) {
        this.manifestMediaType = manifestMediaType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getPullTime() {
        return pullTime;
    }

    public void setPullTime(String pullTime) {
        this.pullTime = pullTime;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public Integer getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Integer repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<TagDTO> getTags() {
        return tags;
    }

    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
