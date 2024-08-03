package help.lixin.starlink.plugin.k8s.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.fabric8.kubernetes.client.utils.Serialization;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/18 下午2:50
 * @Description
 */
public class ConvertUtils {

    public <T> String objectToYAML(T object) throws JsonProcessingException {
        ObjectMapper mapper = Serialization.yamlMapper();
        String yaml = mapper.writeValueAsString(object);
        return yaml;
    }

    public <T> T yamlToObject(String yaml, Class<T> targetType) throws JsonProcessingException {
        return Serialization.yamlMapper().readValue(yaml, targetType);
    }
}
