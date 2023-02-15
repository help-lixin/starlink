package help.lixin.xxl.job.api.model;


public enum ScheduleTypeEnum {
    CRON("CRON"),//cron表达式
    FIX_RATE("FIX_RATE");//固定速率

    private String desc;

    private ScheduleTypeEnum(String desc) {
        this.desc = desc;

    }

    public String getDesc() {
        return desc;
    }

    public static ScheduleTypeEnum match(String name) {
        for (ScheduleTypeEnum item : ScheduleTypeEnum.values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
