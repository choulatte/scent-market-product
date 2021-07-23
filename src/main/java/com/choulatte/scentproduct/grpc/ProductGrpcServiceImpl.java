package com.choulatte.scentproduct.grpc;

import com.choulatte.product.grpc.ProductServiceGrpc;
import com.choulatte.product.grpc.ProductServiceOuterClass;
import com.choulatte.scentproduct.application.PendingService;
import com.choulatte.scentproduct.application.ProductService;
import com.choulatte.scentproduct.domain.StatusType;
import com.choulatte.scentproduct.exception.OngoingProductPresentException;
import com.choulatte.scentproduct.exception.ProductInvalidatingException;
import io.grpc.stub.StreamObserver;
import javafx.util.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductGrpcServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductService productService;
    private final PendingService pendingService;

    @Override
    public void doUserProductsPending(ProductServiceOuterClass.ProductsPendingRequest request, StreamObserver<ProductServiceOuterClass.ProductsPendingResponse> responseObserver) {
        try {
            pendingService.makeUserPending(request.getUserId());
            productService.checkUserProductOngoing(request.getUserId());
            List<Long> productList = productService.updateUserProductsStatus(request.getUserId(), StatusType.PENDING);

            responseObserver.onNext(ProductServiceOuterClass.ProductsPendingResponse.newBuilder()
                    .setResult(ProductServiceOuterClass.ProductsPendingResponse.Result.OK)
                    .addAllProductId(productList).build());
        } catch (OngoingProductPresentException e) {
            responseObserver.onNext(ProductServiceOuterClass.ProductsPendingResponse.newBuilder()
            .setResult(ProductServiceOuterClass.ProductsPendingResponse.Result.CONFLICT).build());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void doUserProductsInvalidating(ProductServiceOuterClass.ProductsInvalidatingRequest request, StreamObserver<ProductServiceOuterClass.ProductsInvalidatingResponse> responseObserver) {
        try {
            pendingService.makeUserInvalid(request.getUserId());
            productService.makeProductsInvalid(request.getUserId());

            responseObserver.onNext(ProductServiceOuterClass.ProductsInvalidatingResponse.newBuilder()
                .setResult(ProductServiceOuterClass.ProductsInvalidatingResponse.Result.OK).build());
        } catch (ProductInvalidatingException e) {
            responseObserver.onNext(ProductServiceOuterClass.ProductsInvalidatingResponse.newBuilder()
                .setResult(ProductServiceOuterClass.ProductsInvalidatingResponse.Result.CONFLICT).build());
        }
        responseObserver.onCompleted();
    }
}
