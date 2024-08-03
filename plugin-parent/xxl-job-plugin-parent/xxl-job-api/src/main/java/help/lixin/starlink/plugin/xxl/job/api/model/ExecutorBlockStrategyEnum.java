package help.lixin.starlink.plugin.xxl.job.api.model;

public enum ExecutorBlockStrategyEnum {

    SERIAL_EXECUTION("SERIAL_EXECUTION"),//单机串行
    DISCARD_LATER("DISCARD_LATER"),//丢弃后续调度
    COVER_EARLY("COVER_EARLY");//覆盖之前调度

    private String desc;
    private ExecutorBlockStrategyEnum(String desc) {
        this.desc = desc;

    }

    public String getDesc() {
        return desc;
    }
    public static ExecutorBlockStrategyEnum match(String name){
        for (ExecutorBlockStrategyEnum item: ExecutorBlockStrategyEnum.values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
