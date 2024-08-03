package help.lixin.starlink.plugin.km.api.dto.cluster;

public class PaginationRangeFilterField {
    private String fieldName;

    private String fieldMinValue;

    private String fieldMaxValue;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldMinValue() {
        return fieldMinValue;
    }

    public void setFieldMinValue(String fieldMinValue) {
        this.fieldMinValue = fieldMinValue;
    }

    public String getFieldMaxValue() {
        return fieldMaxValue;
    }

    public void setFieldMaxValue(String fieldMaxValue) {
        this.fieldMaxValue = fieldMaxValue;
    }
}
