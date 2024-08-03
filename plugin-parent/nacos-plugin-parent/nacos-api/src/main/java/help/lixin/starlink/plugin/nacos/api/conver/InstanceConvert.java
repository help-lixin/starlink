package help.lixin.starlink.plugin.nacos.api.conver;

import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceBeat;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceDetail;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceInfo;
import org.mapstruct.Mapper;

@Mapper
public interface InstanceConvert {

    NacosInstanceBeat convert(NacosInstanceInfo nacosInstanceInfo);

    NacosInstanceDetail convertDetail(NacosInstanceInfo nacosInstanceInfo);
}
