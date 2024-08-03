package help.lixin.starlink.plugin.credential.request;

import help.lixin.starlink.plugin.credential.enums.CredentialEnum;

import java.util.List;

public class SysCredentialOpaqueVO extends CredentialVO {

    private List<OpaqueVO> dataList;

    public List<OpaqueVO> getDataList() {
        return dataList;
    }

    public void setDataList(List<OpaqueVO> dataList) {
        this.dataList = dataList;
    }

    public SysCredentialOpaqueVO() {
        setCredentialType(CredentialEnum.OPAQUE);
    }
}