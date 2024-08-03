package help.lixin.starlink.plugin.nacos.api.model.enums;

public enum NacosSearchEnum {

    ACCURATE("accurate"),
    BLUR("blur");

    private String desc;
    private NacosSearchEnum(String desc) {
        this.desc = desc;

    }

    public String getDesc() {
        return desc;
    }
    public static NacosSearchEnum match(String name){
        for (NacosSearchEnum item: NacosSearchEnum.values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
