package help.lixin.starlink.plugin.nacos.api.dto.config;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/14 5:33 下午
 * @Description
 */
public class NacosPageListConfigResponse<T> {
    private Integer totalCount;

    private Integer pageNumber;

    private Integer pagesAvailable;

    private List<T> pageItems;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPagesAvailable() {
        return pagesAvailable;
    }

    public void setPagesAvailable(Integer pagesAvailable) {
        this.pagesAvailable = pagesAvailable;
    }

    public List<T> getPageItems() {
        return pageItems;
    }

    public void setPageItems(List<T> pageItems) {
        this.pageItems = pageItems;
    }
}
