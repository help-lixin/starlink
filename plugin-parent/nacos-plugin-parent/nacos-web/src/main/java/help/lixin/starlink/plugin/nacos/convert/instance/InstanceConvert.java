package help.lixin.starlink.plugin.nacos.convert.instance;

import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceBeat;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceDetail;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceInfo;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceList;
import help.lixin.starlink.plugin.nacos.request.instance.NacosInstanceBeatRequest;
import help.lixin.starlink.plugin.nacos.request.instance.NacosInstanceDetailRequest;
import help.lixin.starlink.plugin.nacos.request.instance.NacosInstanceInfoRequest;
import help.lixin.starlink.plugin.nacos.request.instance.NacosInstanceListRequest;
import org.mapstruct.Mapper;

@Mapper
public interface InstanceConvert {

    NacosInstanceInfo convert(NacosInstanceInfoRequest nacosInstanceInfoRequest);

    NacosInstanceBeat convert(NacosInstanceBeatRequest nacosInstanceBeat);

    NacosInstanceList convert(NacosInstanceListRequest nacosInstanceListRequest);

    NacosInstanceDetail convert(NacosInstanceDetailRequest nacosInstanceDetailRequest);
}
