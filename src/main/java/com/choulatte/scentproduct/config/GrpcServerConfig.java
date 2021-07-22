package com.choulatte.scentproduct.config;

import com.choulatte.scentproduct.grpc.ProductGrpcServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfig {

    @Value("8090")
    private int grpcServerPort;

    @Bean
    public Server grpcServer(ProductGrpcServiceImpl productGrpcService) {
        return ServerBuilder.forPort(grpcServerPort)
                .addService(productGrpcService).build();
    }
}
