package help.lixin.enums;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/18 下午3:26
 * @Description
 */
public enum  IsDelEnum {
    DELETE(1),
    NORMAL(0);

    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    IsDelEnum(Integer value) {
        this.value = value;
    }
}
