package help.lixin.starlink.plugin.km.api.dto.cluster;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="列表分页查询-过滤字段信息")
public class PaginationRangeFilterFieldDTO {
    @ApiModelProperty(value="过滤字段", example = "healthScore")
    private String fieldName;

    @ApiModelProperty(value="最小值", example = "2")
    private String fieldMinValue;

    @ApiModelProperty(value="最大值", example = "100")
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
