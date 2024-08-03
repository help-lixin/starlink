package help.lixin.starlink.plugin.credential.dto;

import java.util.List;

public class SysCredentialOpaqueDTO extends SysCredentialCommonDTO {

    private List<CredentialOptionDTO> dataList;

    public List<CredentialOptionDTO> getDataList() {
        return dataList;
    }

    public void setDataList(List<CredentialOptionDTO> dataList) {
        this.dataList = dataList;
    }
}