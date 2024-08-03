package help.lixin.enums;

public enum StatusEnum {
    DELETE(0),
    NORMAL(1),
    DOING(10);

    private Integer value;

    private StatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
