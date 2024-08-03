package help.lixin.starlink.plugin.km.api.dto.cluster;

import java.util.List;


public class PaginationPreciseFilterField {
    private String fieldName;

    private List<String> fieldValueList;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public List<String> getFieldValueList() {
        return fieldValueList;
    }

    public void setFieldValueList(List<String> fieldValueList) {
        this.fieldValueList = fieldValueList;
    }
}
