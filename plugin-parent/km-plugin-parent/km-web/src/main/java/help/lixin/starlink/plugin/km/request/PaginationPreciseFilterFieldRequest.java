package help.lixin.starlink.plugin.km.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel(description="列表分页查询-过滤字段信息")
public class PaginationPreciseFilterFieldRequest {
    @NotBlank(message = "fieldName不允许为空")
    @ApiModelProperty(value="过滤字段")
    private String fieldName;

    @NotNull(message = "fieldValueList不允许为空")
    @ApiModelProperty(value="过滤值")
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
