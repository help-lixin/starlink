package help.lixin.starlink.core.utils;

import help.lixin.starlink.core.dto.EnvDTO;
import help.lixin.exception.ServiceException;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/1 11:03 下午
 * @Description
 */
public class PluginUtils {

    public static EnvDTO envParse(String envName){
        if (StringUtils.isBlank(envName)){
            return null;
        }
        try {
            String[] envArr = envName.split(":");

            if (envArr.length == 3) {
                EnvDTO envDTO = new EnvDTO();
                envDTO.setEnvCode(envArr[0]);
                envDTO.setGroupCode(envArr[1]);
                envDTO.setInstanceCode(envArr[2]);

                return envDTO;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new ServiceException("获取环境信息发生异常");
        }

        return null;
    }
}
