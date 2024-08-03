package help.lixin.starlink.plugin.xxl.job.api.model;


public enum ExecutorRouteStrategyEnum {
    FIRST("FIRST"),//第一个
    LAST("LAST"),//最后一个
    ROUND("ROUND"),//轮询
    RANDOM("RANDOM"),//随机
    CONSISTENT_HASH("CONSISTENT_HASH"),//一致性HASH
    LEAST_FREQUENTLY_USED("LEAST_FREQUENTLY_USED"),//最不经常使用
    LEAST_RECENTLY_USED("LEAST_RECENTLY_USED"),//最近最久未使用
    FAILOVER("FAILOVER"),//故障转移
    BUSYOVER("BUSYOVER"),//忙碌转移
    SHARDING_BROADCAST("SHARDING_BROADCAST");//分布广播

    private String desc;

    private ExecutorRouteStrategyEnum(String desc) {
        this.desc = desc;

    }

    public String getDesc() {
        return desc;
    }

    public static ExecutorRouteStrategyEnum match(String name) {
        for (ExecutorRouteStrategyEnum item : ExecutorRouteStrategyEnum.values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
