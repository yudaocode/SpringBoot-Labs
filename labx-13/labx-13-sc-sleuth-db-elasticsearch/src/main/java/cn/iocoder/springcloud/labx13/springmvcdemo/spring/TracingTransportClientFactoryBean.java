package cn.iocoder.springcloud.labx13.springmvcdemo.spring;

import io.opentracing.Tracer;
import io.opentracing.contrib.elasticsearch6.TracingPreBuiltTransportClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.elasticsearch.client.TransportClientFactoryBean;

import java.util.Properties;

/**
 * 参考 {@link TransportClientFactoryBean} 来实现。
 */
public class TracingTransportClientFactoryBean implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(TransportClientFactoryBean.class);
    private ClusterNodes clusterNodes = ClusterNodes.of("127.0.0.1:9300");
    private String clusterName = "elasticsearch";
    private Boolean clientTransportSniff = true;
    private Boolean clientIgnoreClusterName = Boolean.FALSE;
    private String clientPingTimeout = "5s";
    private String clientNodesSamplerInterval = "5s";
    private TransportClient client;
    private Properties properties;

    private Tracer tracer;

    public TracingTransportClientFactoryBean(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public void destroy() throws Exception {
        try {
            logger.info("Closing elasticSearch  client");
            if (client != null) {
                client.close();
            }
        } catch (final Exception e) {
            logger.error("Error closing ElasticSearch client: ", e);
        }
    }

    @Override
    public TransportClient getObject() throws Exception {
        return client;
    }

    @Override
    public Class<TransportClient> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        buildClient();
    }

    protected void buildClient() throws Exception {
        // 创建可追踪的 TracingPreBuiltTransportClient
        client = new TracingPreBuiltTransportClient(tracer, settings());

        clusterNodes.stream() //
                .peek(it -> logger.info("Adding transport node : " + it.toString())) //
                .forEach(client::addTransportAddress);

        client.connectedNodes();
    }

    private Settings settings() {
        if (properties != null) {
            Settings.Builder builder = Settings.builder();

            properties.forEach((key, value) -> {
                builder.put(key.toString(), value.toString());
            });

            return builder.build();
        }
        return Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.sniff", clientTransportSniff)
                .put("client.transport.ignore_cluster_name", clientIgnoreClusterName)
                .put("client.transport.ping_timeout", clientPingTimeout)
                .put("client.transport.nodes_sampler_interval", clientNodesSamplerInterval)
                .build();
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = ClusterNodes.of(clusterNodes);
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public void setClientTransportSniff(Boolean clientTransportSniff) {
        this.clientTransportSniff = clientTransportSniff;
    }

    public String getClientNodesSamplerInterval() {
        return clientNodesSamplerInterval;
    }

    public void setClientNodesSamplerInterval(String clientNodesSamplerInterval) {
        this.clientNodesSamplerInterval = clientNodesSamplerInterval;
    }

    public String getClientPingTimeout() {
        return clientPingTimeout;
    }

    public void setClientPingTimeout(String clientPingTimeout) {
        this.clientPingTimeout = clientPingTimeout;
    }

    public Boolean getClientIgnoreClusterName() {
        return clientIgnoreClusterName;
    }

    public void setClientIgnoreClusterName(Boolean clientIgnoreClusterName) {
        this.clientIgnoreClusterName = clientIgnoreClusterName;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
