package com.choulatte.scentproduct.grpc;

import com.choulatte.product.grpc.ProductServiceGrpc;
import com.choulatte.product.grpc.ProductServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductGrpcServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    @Override
    public void doUserProductsPending(ProductServiceOuterClass.ProductsPendingRequest request, StreamObserver<ProductServiceOuterClass.ProductsPendingResponse> responseObserver) {

    }
}
