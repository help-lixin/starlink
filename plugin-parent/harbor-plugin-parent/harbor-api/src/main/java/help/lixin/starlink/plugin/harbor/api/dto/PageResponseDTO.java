package help.lixin.starlink.plugin.harbor.api.dto;

import java.util.List;

public class PageResponseDTO {
    private List<LogInfoDTO> records;
    private long total = 0;
    private long pageSize = 10;
    private long pageCurrent = 1;

    public List<LogInfoDTO> getRecords() {
        return records;
    }

    public void setRecords(List<LogInfoDTO> records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(long pageCurrent) {
        this.pageCurrent = pageCurrent;
    }
}
