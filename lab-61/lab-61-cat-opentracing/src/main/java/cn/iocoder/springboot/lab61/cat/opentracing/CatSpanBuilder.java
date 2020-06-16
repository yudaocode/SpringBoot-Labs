package cn.iocoder.springboot.lab61.cat.opentracing;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Transaction;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.tag.Tag;

import java.util.LinkedList;
import java.util.List;

public class CatSpanBuilder implements Tracer.SpanBuilder {

    private List<cn.iocoder.springboot.lab61.cat.opentracing.Tag> tags = new LinkedList<>();
    private String operationName;

    public CatSpanBuilder(String operationName) {
        this.operationName = operationName;
    }

    public Tracer.SpanBuilder asChildOf(SpanContext parent) {
        return null;
    }

    public Tracer.SpanBuilder asChildOf(Span parent) {
        return null;
    }

    public Tracer.SpanBuilder addReference(String referenceType, SpanContext referencedContext) {
        return null;
    }

    public Tracer.SpanBuilder ignoreActiveSpan() {
        return null;
    }

    public Tracer.SpanBuilder withTag(String key, String value) {
        tags.add(new cn.iocoder.springboot.lab61.cat.opentracing.Tag(key, value));
        return this;
    }

    public Tracer.SpanBuilder withTag(String key, boolean value) {
        return null;
    }

    public Tracer.SpanBuilder withTag(String key, Number value) {
        return null;
    }

    public <T> Tracer.SpanBuilder withTag(Tag<T> tag, T value) {
        return null;
    }

    public Tracer.SpanBuilder withStartTimestamp(long microseconds) {
        return null;
    }

    public Span start() {
        Transaction transaction = Cat.newTransaction("Opentracing", operationName);
        // TODO tag 的处理
        return new CatSpan(transaction);
    }

}
