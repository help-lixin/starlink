package help.lixin.starlink.plugin.km.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import help.lixin.starlink.plugin.km.api.dto.cluster.Metric;
import help.lixin.starlink.plugin.km.api.model.enums.SortTypeEnum;
import help.lixin.starlink.request.BasePageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(tags = "集群列表请求对象")
public class MultiClusterDashboardRequest extends BasePageRequest {
    @NotNull(message = "latestMetricNames不允许为空")
    @ApiModelProperty("需要指标点的信息")
    private List<String> latestMetricNames;

    @NotNull(message = "metricLines不允许为空")
    @ApiModelProperty("需要指标曲线的信息")
    private Metric metricLines;

    @ApiModelProperty(value = "排序字段, 传入的字段名同返回的VO里面的字段名")
    private String sortField;

    @ApiModelProperty(value = "排序类型[asc|desc]，默认desc", example = "desc")
    private String sortType = SortTypeEnum.DESC.getSortType();

    @Valid
    @ApiModelProperty(value = "多字段精确过滤")
    private List<PaginationPreciseFilterFieldRequest> preciseFilterDTOList;

    @Valid
    @ApiModelProperty(value = "多字段范围过滤")
    private List<PaginationRangeFilterFieldRequest> rangeFilterDTOList;

    @ApiModelProperty(value = "模糊搜索", example = "")
    private String searchKeywords;

    public List<String> getLatestMetricNames() {
        return latestMetricNames;
    }

    public void setLatestMetricNames(List<String> latestMetricNames) {
        this.latestMetricNames = latestMetricNames;
    }

    public Metric getMetricLines() {
        return metricLines;
    }

    public void setMetricLines(Metric metricLines) {
        this.metricLines = metricLines;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public List<PaginationPreciseFilterFieldRequest> getPreciseFilterDTOList() {
        return preciseFilterDTOList;
    }

    public void setPreciseFilterDTOList(List<PaginationPreciseFilterFieldRequest> preciseFilterDTOList) {
        this.preciseFilterDTOList = preciseFilterDTOList;
    }

    public List<PaginationRangeFilterFieldRequest> getRangeFilterDTOList() {
        return rangeFilterDTOList;
    }

    public void setRangeFilterDTOList(List<PaginationRangeFilterFieldRequest> rangeFilterDTOList) {
        this.rangeFilterDTOList = rangeFilterDTOList;
    }

    public String getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(String searchKeywords) {
        this.searchKeywords = searchKeywords;
    }
}
