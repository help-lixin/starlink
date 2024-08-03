package help.lixin.starlink.plugin.k8s.service;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;

import java.util.Map;

public interface INamespaceApiService {

    Namespace createNameSpace(Namespace namespace);

    Boolean deleteNameSpace(String name);

    NamespaceList queryNameSpaces();

    Namespace queryNameSpaceByName(String name);

    Namespace saveLabels(String name,Map<String,String> labels);

    Namespace removeLabel(String name,String labelName);

}
