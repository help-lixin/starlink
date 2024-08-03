package help.lixin.starlink.plugin.harbor.convert;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import help.lixin.starlink.plugin.harbor.api.dto.LogInfo;
import help.lixin.starlink.plugin.harbor.api.dto.LogInfoDTO;

@Mapper
public interface HarborLoginfoConvert {

    @Mapping(target = "opTime", source = "op_time")
    @Mapping(target = "resourceType", source = "resource_type")
    LogInfoDTO convert(LogInfo logInfo);

    @Mapping(target = "opTime", source = "op_time")
    @Mapping(target = "resourceType", source = "resource_type")
    List<LogInfoDTO> convert(List<LogInfo> logInfos);
}
