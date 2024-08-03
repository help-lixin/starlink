package help.lixin.starlink.plugin.km.api.dto.cluster;

import help.lixin.starlink.plugin.km.api.model.enums.SortTypeEnum;

import java.util.List;


public class MultiClusterDashboard {
    private List<String> latestMetricNames;

    private Metric metricLines;

    private String sortField;

    private String sortType = SortTypeEnum.DESC.getSortType();

    private List<PaginationPreciseFilterField> preciseFilterDTOList;

    private List<PaginationRangeFilterField> rangeFilterDTOList;

    private Integer pageNo;

    private Integer pageSize;

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

    public List<PaginationPreciseFilterField> getPreciseFilterDTOList() {
        return preciseFilterDTOList;
    }

    public void setPreciseFilterDTOList(List<PaginationPreciseFilterField> preciseFilterDTOList) {
        this.preciseFilterDTOList = preciseFilterDTOList;
    }

    public List<PaginationRangeFilterField> getRangeFilterDTOList() {
        return rangeFilterDTOList;
    }

    public void setRangeFilterDTOList(List<PaginationRangeFilterField> rangeFilterDTOList) {
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
