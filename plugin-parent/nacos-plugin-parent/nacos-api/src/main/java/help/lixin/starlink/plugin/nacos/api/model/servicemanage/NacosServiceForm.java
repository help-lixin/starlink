/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package help.lixin.starlink.plugin.nacos.api.model.servicemanage;

import java.io.Serializable;

/**
 * ServiceForm.
 * @author dongyafei
 * @date 2022/9/7
 */
public class NacosServiceForm implements Serializable {
    
    private static final long serialVersionUID = -4905650083916616115L;
    
    private String namespaceId;
    
    private String serviceName;
    
    private String groupName;
    
    private Boolean ephemeral;
    
    private Float protectThreshold;
    
    private String metadata;
    
    private String selector;

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Boolean getEphemeral() {
        return ephemeral;
    }

    public void setEphemeral(Boolean ephemeral) {
        this.ephemeral = ephemeral;
    }

    public Float getProtectThreshold() {
        return protectThreshold;
    }

    public void setProtectThreshold(Float protectThreshold) {
        this.protectThreshold = protectThreshold;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }
}
