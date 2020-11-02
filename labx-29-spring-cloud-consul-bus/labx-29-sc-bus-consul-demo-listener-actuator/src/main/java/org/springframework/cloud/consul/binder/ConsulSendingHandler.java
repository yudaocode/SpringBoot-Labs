package org.springframework.cloud.consul.binder;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.event.model.Event;
import com.ecwid.consul.v1.event.model.EventParams;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;

/**
 * Adapter that converts and sends Messages as Consul events.
 *
 * @author Spencer Gibb
 */
public class ConsulSendingHandler extends AbstractMessageHandler {

    private final ConsulClient consul;

    private final String eventName;

    public ConsulSendingHandler(ConsulClient consul, String eventName) {
        this.consul = consul;
        this.eventName = eventName;
    }

    @Override
    protected void handleMessageInternal(Message<?> message) {
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("Publishing message" + message);
        }

        // 转换成 String
        Object payload = message.getPayload();
        if (payload instanceof byte[]) {
            payload = new String((byte[]) payload);
        }

        // TODO: support headers
        // TODO: support consul event filters: NodeFilter, ServiceFilter, TagFilter
        Response<Event> event = this.consul.eventFire(this.eventName, (String) payload,
                new EventParams(), QueryParams.DEFAULT);
        // TODO: return event?
    }

}
