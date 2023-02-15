package help.lixin.xxl.job.api.model;


public enum MisfireStrategyEnum {
    DO_NOTHING("DO_NOTHING"),//忽略
    FIRE_ONCE_NOW("FIRE_ONCE_NOW");//立即执行一次

    private String desc;

    private MisfireStrategyEnum(String desc) {
        this.desc = desc;

    }

    public String getDesc() {
        return desc;
    }

    public static MisfireStrategyEnum match(String name) {
        for (MisfireStrategyEnum item : MisfireStrategyEnum.values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
