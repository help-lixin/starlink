package help.lixin.starlink.plugin.k8s.service;

import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeList;

import java.util.Map;

public interface INodeApiService {

    NodeList queryNods();

    Node queryNod(String nodeName);

    Node saveNodeLabel(String nodeName, Map<String,String> labels);

    Node deleteNodeLabel(String nodeName, String labelName);
}
