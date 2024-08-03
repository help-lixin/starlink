package help.lixin.starlink.plugin.km.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(description="列表分页查询-过滤字段信息")
public class PaginationRangeFilterFieldRequest {
    @NotBlank(message = "fieldName不允许为空")
    @ApiModelProperty(value="过滤字段", example = "healthScore")
    private String fieldName;

    @NotBlank(message = "fieldMinValue不允许为空")
    @ApiModelProperty(value="最小值", example = "2")
    private String fieldMinValue;

    @NotBlank(message = "fieldMaxValue不允许为空")
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
