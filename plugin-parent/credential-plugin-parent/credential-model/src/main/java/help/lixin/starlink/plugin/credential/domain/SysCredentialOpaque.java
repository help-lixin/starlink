package help.lixin.starlink.plugin.credential.domain;

import java.util.ArrayList;
import java.util.List;

import help.lixin.starlink.plugin.credential.dto.CredentialOptionDTO;

/**
 * Table: sys_credential_opaque
 */
public class SysCredentialOpaque extends SysCredentialCommon {

    private Long commonId;

    /**
     * Column:    opq_key
     * Nullable:  true
     */
    private String opqKey;

    /**
     * Column:    opq_value
     * Nullable:  true
     */
    private String opqValue;

    private List<CredentialOptionDTO> dataList = new ArrayList<>();

    public Long getCommonId() {
        return commonId;
    }

    public void setCommonId(Long commonId) {
        this.commonId = commonId;
    }

    public List<CredentialOptionDTO> getDataList() {
        return dataList;
    }

    public void setDataList(List<CredentialOptionDTO> dataList) {
        this.dataList = dataList;
    }

    public String getOpqKey() {
        return opqKey;
    }

    public void setOpqKey(String opqKey) {
        this.opqKey = opqKey == null ? null : opqKey.trim();
    }

    public String getOpqValue() {
        return opqValue;
    }

    public void setOpqValue(String opqValue) {
        this.opqValue = opqValue == null ? null : opqValue.trim();
    }
}