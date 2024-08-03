package help.lixin.starlink.plugin.xxl.job.request.group;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import help.lixin.starlink.request.EnvRequest;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 2:57 下午
 * @Description
 */
public class JobGroupUpdateVO {
    private int id;
    private String appname;
    private String title;
    private int addressType; // 执行器地址类型：0=自动注册、1=手动录入
    private String addressList; // 执行器地址列表，多地址逗号分隔(手动录入)
    @Valid
    private EnvRequest envRequest;

    // registry list
    private List<String> registryList; // 执行器地址列表(系统注册)

    @Transient
    public List<String> getRegistryList() {
        if (addressList != null && addressList.trim().length() > 0) {
            registryList = new ArrayList<String>(Arrays.asList(addressList.split(",")));
        }
        return registryList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    public String getAddressList() {
        return addressList;
    }

    public void setAddressList(String addressList) {
        this.addressList = addressList;
    }

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }

    public void setRegistryList(List<String> registryList) {
        this.registryList = registryList;
    }
}
