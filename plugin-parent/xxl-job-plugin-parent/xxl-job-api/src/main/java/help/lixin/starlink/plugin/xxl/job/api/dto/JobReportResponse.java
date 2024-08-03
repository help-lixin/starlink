package help.lixin.starlink.plugin.xxl.job.api.dto;

import java.util.*;

public class JobReportResponse {
    private List<String> triggerDayList = new ArrayList<>();
    private List<String> triggerDayCountRunningList = new ArrayList<>();
    private List<Integer> triggerDayCountSucList = new ArrayList<Integer>();
    private List<Integer> triggerDayCountFailList = new ArrayList<Integer>();
    private int triggerCountRunningTotal = 0;
    private int triggerCountSucTotal = 0;
    private int triggerCountFailTotal = 0;

    public List<String> getTriggerDayList() {
        return triggerDayList;
    }

    public void setTriggerDayList(List<String> triggerDayList) {
        this.triggerDayList = triggerDayList;
    }

    public List<String> getTriggerDayCountRunningList() {
        return triggerDayCountRunningList;
    }

    public void setTriggerDayCountRunningList(List<String> triggerDayCountRunningList) {
        this.triggerDayCountRunningList = triggerDayCountRunningList;
    }

    public List<Integer> getTriggerDayCountSucList() {
        return triggerDayCountSucList;
    }

    public void setTriggerDayCountSucList(List<Integer> triggerDayCountSucList) {
        this.triggerDayCountSucList = triggerDayCountSucList;
    }

    public List<Integer> getTriggerDayCountFailList() {
        return triggerDayCountFailList;
    }

    public void setTriggerDayCountFailList(List<Integer> triggerDayCountFailList) {
        this.triggerDayCountFailList = triggerDayCountFailList;
    }

    public int getTriggerCountRunningTotal() {
        return triggerCountRunningTotal;
    }

    public void setTriggerCountRunningTotal(int triggerCountRunningTotal) {
        this.triggerCountRunningTotal = triggerCountRunningTotal;
    }

    public int getTriggerCountSucTotal() {
        return triggerCountSucTotal;
    }

    public void setTriggerCountSucTotal(int triggerCountSucTotal) {
        this.triggerCountSucTotal = triggerCountSucTotal;
    }

    public int getTriggerCountFailTotal() {
        return triggerCountFailTotal;
    }

    public void setTriggerCountFailTotal(int triggerCountFailTotal) {
        this.triggerCountFailTotal = triggerCountFailTotal;
    }
}
