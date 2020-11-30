package cn.iocoder.springboot.lab61.cat.opentracing;

import io.opentracing.*;
import io.opentracing.propagation.Format;

public class CatTracer implements Tracer {

    public ScopeManager scopeManager() {
        return null;
    }

    public Span activeSpan() {
        return null;
    }

    public Scope activateSpan(Span span) {
        return null;
    }

    public SpanBuilder buildSpan(String operationName) {
        return new CatSpanBuilder(operationName);
    }

    public <C> void inject(SpanContext spanContext, Format<C> format, C carrier) {

    }

    public <C> SpanContext extract(Format<C> format, C carrier) {
        return null;
    }

    public void close() {

    }

}
