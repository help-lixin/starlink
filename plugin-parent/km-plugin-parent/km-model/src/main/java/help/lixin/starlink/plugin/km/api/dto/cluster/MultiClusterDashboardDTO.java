package help.lixin.starlink.plugin.km.api.dto.cluster;

import help.lixin.starlink.plugin.km.api.model.enums.SortTypeEnum;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


public class MultiClusterDashboardDTO {
    @ApiModelProperty("需要指标点的信息( \"HealthScore\",\n" +
            "        \"HealthCheckPassed\",\n" +
            "        \"HealthCheckTotal\",\n" +
            "        \"Brokers\",\n" +
            "        \"Zookeepers\",\n" +
            "        \"LeaderMessages\",\n" +
            "        \"TotalLogSize\",\n" +
            "        \"BytesIn\",\n" +
            "        \"BytesOut\")")
    private List<String> latestMetricNames;

    @ApiModelProperty("需要指标曲线的信息")
    private MetricDTO metricLines;

    @ApiModelProperty(value="排序字段, 传入的字段名同返回的VO里面的字段名")
    private String sortField;

    @ApiModelProperty(value="排序类型[asc|desc]，默认desc", example = "desc")
    private String sortType = SortTypeEnum.DESC.getSortType();

    @ApiModelProperty(value="多字段精确过滤")
    private List<PaginationPreciseFilterFieldDTO> preciseFilterDTOList;

    @ApiModelProperty(value="多字段范围过滤")
    private List<PaginationRangeFilterFieldDTO> rangeFilterDTOList;

    @ApiModelProperty(value="页面位置，默认1", example = "1")
    private Integer pageNo;

    @ApiModelProperty(value="页面大小，默认10", example = "10")
    private Integer pageSize;

    @ApiModelProperty(value="模糊搜索", example = "")
    private String searchKeywords;

    public List<String> getLatestMetricNames() {
        return latestMetricNames;
    }

    public void setLatestMetricNames(List<String> latestMetricNames) {
        this.latestMetricNames = latestMetricNames;
    }

    public MetricDTO getMetricLines() {
        return metricLines;
    }

    public void setMetricLines(MetricDTO metricLines) {
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

    public List<PaginationPreciseFilterFieldDTO> getPreciseFilterDTOList() {
        return preciseFilterDTOList;
    }

    public void setPreciseFilterDTOList(List<PaginationPreciseFilterFieldDTO> preciseFilterDTOList) {
        this.preciseFilterDTOList = preciseFilterDTOList;
    }

    public List<PaginationRangeFilterFieldDTO> getRangeFilterDTOList() {
        return rangeFilterDTOList;
    }

    public void setRangeFilterDTOList(List<PaginationRangeFilterFieldDTO> rangeFilterDTOList) {
        this.rangeFilterDTOList = rangeFilterDTOList;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchKeywords() {
        return searchKeywords;
    }

    public void setSearchKeywords(String searchKeywords) {
        this.searchKeywords = searchKeywords;
    }
}
