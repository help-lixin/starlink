package help.lixin.starlink.plugin.eureka.request;

public enum InstanceStatus {
    UP,
    DOWN,
    STARTING,
    OUT_OF_SERVICE,
    UNKNOWN;

    private InstanceStatus() {
    }

}