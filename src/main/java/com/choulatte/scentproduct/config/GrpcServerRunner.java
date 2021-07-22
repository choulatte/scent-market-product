package com.choulatte.scentproduct.config;

import io.grpc.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GrpcServerRunner implements ApplicationRunner, DisposableBean {

    private final Server grpcServer;

    @Override
    public void destroy() throws Exception {
        if (grpcServer != null) {
            grpcServer.shutdown();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        grpcServer.start();
        grpcServer.awaitTermination();
    }
}
