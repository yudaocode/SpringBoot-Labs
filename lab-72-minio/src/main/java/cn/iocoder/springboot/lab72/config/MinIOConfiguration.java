package cn.iocoder.springboot.lab72.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.SetBucketPolicyArgs;
import io.minio.errors.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Configuration
public class MinIOConfiguration {

    @Bean
    public MinioClient minioClient() {
        // Minio 配置。实际项目中，定义到 application.yml 配置文件中
        String endpoint = "http://127.0.0.1:9000";
        String accessKey = "admin";
        String secretKey = "password";

        // 创建 MinioClient 客户端
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

}
