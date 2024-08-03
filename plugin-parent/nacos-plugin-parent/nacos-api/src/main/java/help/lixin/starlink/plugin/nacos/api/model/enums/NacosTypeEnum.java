package help.lixin.starlink.plugin.nacos.api.model.enums;

public enum NacosTypeEnum {

    TEXT("text"),
    JSON("json"),
    XML("xml"),
    YAML("yaml"),
    HTML("html"),
    PROPERTIES("properties");

    private String desc;
    private NacosTypeEnum(String desc) {
        this.desc = desc;

    }

    public String getDesc() {
        return desc;
    }
    public static NacosTypeEnum match(String name){
        for (NacosTypeEnum item: NacosTypeEnum.values()) {
            if (item.name().equals(name)) {
                return item;
            }
        }
        return null;
    }
}
