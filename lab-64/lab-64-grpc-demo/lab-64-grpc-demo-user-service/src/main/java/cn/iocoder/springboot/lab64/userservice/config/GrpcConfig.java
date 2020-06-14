package cn.iocoder.springboot.lab64.userservice.config;

import cn.iocoder.springboot.lab64.userservice.rpc.UserServiceGrpcImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class GrpcConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * gRPC Server 端口
     */
    private static final Integer GRPC_PORT = 8888;

    @Bean
    public Server grpcServer(final UserServiceGrpcImpl userServiceGrpc) throws IOException {
        // 创建 gRPC Server 对象
        Server server = ServerBuilder.forPort(GRPC_PORT)
                .addService(userServiceGrpc)
                .build();
        // 启动 gRPC Server
        server.start();
        logger.info("[grpcServer][启动完成，端口为({})]", server.getPort());
        return server;
    }

}
