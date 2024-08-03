package help.lixin.starlink.plugin.xxl.job.api.dto;

import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobGroup;

import java.util.ArrayList;
import java.util.List;

public class JobGroupResponse {
    // 总记录数
    private int recordsTotal;
    // 过滤后的总记录数
    private int recordsFiltered;
    private List<XxlJobGroup> data = new ArrayList<>();

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<XxlJobGroup> getData() {
        return data;
    }

    public void setData(List<XxlJobGroup> data) {
        if (null != data) {
            this.data = data;
        }
    }
}
