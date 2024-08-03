/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
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
import java.util.List;
import java.util.Map;

public class NacosClusterInfo implements Serializable {

    private static final long serialVersionUID = 2146881454057032105L;

    private String clusterName;
    
    private String healthChecker;
    
    private Map<String, String> metadata;
    
    private List<NacosIpAddressInfo> hosts;
    
    /**
     * Getter method for property <tt>hosts</tt>.
     *
     * @return property value of hosts
     */
    public List<NacosIpAddressInfo> getHosts() {
        return hosts;
    }
    
    /**
     * Setter method for property <tt>hosts </tt>.
     *
     * @param hosts value to be assigned to property hosts
     */
    public void setHosts(List<NacosIpAddressInfo> hosts) {
        this.hosts = hosts;
    }
    
    public String getClusterName() {
        return clusterName;
    }
    
    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getHealthChecker() {
        return healthChecker;
    }

    public void setHealthChecker(String healthChecker) {
        this.healthChecker = healthChecker;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }
    
    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
