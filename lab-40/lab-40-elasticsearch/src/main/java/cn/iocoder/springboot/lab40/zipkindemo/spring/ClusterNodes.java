package cn.iocoder.springboot.lab40.zipkindemo.spring;

import org.elasticsearch.common.transport.TransportAddress;
import org.springframework.data.util.Streamable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

class ClusterNodes implements Streamable<TransportAddress> {

    public static ClusterNodes DEFAULT = ClusterNodes.of("127.0.0.1:9300");

    private static final String COLON = ":";
    private static final String COMMA = ",";

    private final List<TransportAddress> clusterNodes;

    /**
     * Creates a new {@link ClusterNodes} by parsing the given source.
     *
     * @param source must not be {@literal null} or empty.
     */
    private ClusterNodes(String source) {

        Assert.hasText(source, "Cluster nodes source must not be null or empty!");

        String[] nodes = StringUtils.delimitedListToStringArray(source, COMMA);

        this.clusterNodes = Arrays.stream(nodes).map(node -> {

            String[] segments = StringUtils.delimitedListToStringArray(node, COLON);

            Assert.isTrue(segments.length == 2,
                    () -> String.format("Invalid cluster node %s in %s! Must be in the format host:port!", node, source));

            String host = segments[0].trim();
            String port = segments[1].trim();

            Assert.hasText(host, () -> String.format("No host name given cluster node %s!", node));
            Assert.hasText(port, () -> String.format("No port given in cluster node %s!", node));

            return new TransportAddress(toInetAddress(host), Integer.valueOf(port));

        }).collect(Collectors.toList());
    }

    /**
     * Creates a new {@link ClusterNodes} by parsing the given source. The expected format is a comma separated list of
     * host-port-combinations separated by a colon: {@code host:port,host:port,…}.
     *
     * @param source must not be {@literal null} or empty.
     * @return
     */
    public static ClusterNodes of(String source) {
        return new ClusterNodes(source);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    @Override
    public Iterator<TransportAddress> iterator() {
        return clusterNodes.iterator();
    }

    private static InetAddress toInetAddress(String host) {

        try {
            return InetAddress.getByName(host);
        } catch (UnknownHostException o_O) {
            throw new IllegalArgumentException(o_O);
        }
    }
}
