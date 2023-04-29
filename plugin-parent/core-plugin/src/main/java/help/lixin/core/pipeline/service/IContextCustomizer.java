package help.lixin.core.pipeline.service;

import java.util.Map;

public interface IContextCustomizer {
    void customizer(Map<String, Object> context);
}
