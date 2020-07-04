package cn.iocoder.springcloud.labx21.zuuldemo.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class ApiFallbackProvider implements FallbackProvider {

    public String getRoute() {
        return "*";
    }

    public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
        return new ClientHttpResponse() {

            public HttpStatus getStatusCode() {
                return HttpStatus.OK;
            }

            public int getRawStatusCode() {
                return HttpStatus.OK.value();
            }

            public String getStatusText() {
                return HttpStatus.OK.getReasonPhrase();
            }

            public void close() {}

            public InputStream getBody() { // 响应内容
                String bodyText = String.format("{\"code\": 500,\"message\": \"Service unavailable:%s\"}", cause.getMessage());
                return new ByteArrayInputStream(bodyText.getBytes());
            }

            public HttpHeaders getHeaders() { // 响应头
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON); // json 返回
                return headers;
            }

        };
    }

}
