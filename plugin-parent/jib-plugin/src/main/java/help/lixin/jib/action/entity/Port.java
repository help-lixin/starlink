package help.lixin.jib.action.entity;

import java.util.Objects;

public class Port {
    private static final String TCP_PROTOCOL = "tcp";
    private static final String UDP_PROTOCOL = "udp";

    /**
     * Create a new {@link com.google.cloud.tools.jib.api.buildplan.Port} with TCP protocol.
     *
     * @param port the port number
     * @return the new {@link com.google.cloud.tools.jib.api.buildplan.Port}
     */
    public static Port tcp(int port) {
        return new Port(port, TCP_PROTOCOL);
    }

    /**
     * Create a new {@link com.google.cloud.tools.jib.api.buildplan.Port} with UDP protocol.
     *
     * @param port the port number
     * @return the new {@link com.google.cloud.tools.jib.api.buildplan.Port}
     */
    public static Port udp(int port) {
        return new Port(port, UDP_PROTOCOL);
    }

    /**
     * Gets a {@link com.google.cloud.tools.jib.api.buildplan.Port} with protocol parsed from the string form {@code protocolString}. Unknown
     * protocols will default to TCP.
     *
     * @param port           the port number
     * @param protocolString the case insensitive string (e.g. "tcp", "udp")
     * @return the {@link com.google.cloud.tools.jib.api.buildplan.Port}
     */
    public static Port parseProtocol(int port, String protocolString) {
        String protocol = UDP_PROTOCOL.equalsIgnoreCase(protocolString) ? UDP_PROTOCOL : TCP_PROTOCOL;
        return new Port(port, protocol);
    }

    private final int port;
    private final String protocol;

    private Port(int port, String protocol) {
        this.port = port;
        this.protocol = protocol;
    }

    /**
     * Gets the port number.
     *
     * @return the port number
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets the protocol.
     *
     * @return the protocol
     */
    public String getProtocol() {
        return protocol;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Port)) {
            return false;
        }
        Port otherPort = (Port) other;
        return port == otherPort.port && protocol.equals(otherPort.protocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(port, protocol);
    }

    /**
     * Stringifies the port with protocol, in the form {@code <port>/<protocol>}. For example: {@code
     * 1337/TCP}.
     *
     * @return the string form of the port with protocol
     */
    @Override
    public String toString() {
        return port + "/" + protocol;
    }
}