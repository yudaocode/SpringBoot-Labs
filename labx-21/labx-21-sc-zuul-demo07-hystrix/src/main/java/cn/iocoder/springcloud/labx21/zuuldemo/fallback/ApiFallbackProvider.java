package cn.iocoder.springcloud.labx21.zuuldemo.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ApiFallbackProvider implements FallbackProvider {

    public String getRoute() {
        return "*";
    }

    public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
        return new ClientHttpResponse() {

            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            public String getStatusText() throws IOException {
                return "OK";
            }

            public void close() {}

            public InputStream getBody() throws IOException {
                String bodyText = String.format("{\"code\": 500,\"message\": \"Service unavailable:%s\"}", cause.getMessage());
                return new ByteArrayInputStream(bodyText.getBytes());
            }

            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON); // json 返回
                return headers;
            }

        };
    }

}
