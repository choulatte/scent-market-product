package com.choulatte.scentproduct.grpc;

import com.choulatte.scentproduct.application.BiddingService;
import com.choulatte.scentproduct.application.PendingService;
import com.choulatte.scentproduct.application.ProductService;
import com.choulatte.scentproduct.dto.ProductDetailResDTO;
import com.choulatte.scentproduct.exception.OngoingProductPresentException;
import com.choulatte.scentproduct.exception.ProductBadRequestException;
import com.choulatte.scentproduct.exception.ProductInvalidatingException;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductGrpcServiceImpl extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductService productService;
    private final PendingService pendingService;
    private final BiddingService biddingService;

    @Override
    public void doUserProductsPending(ProductServiceOuterClass.ProductsPendingRequest request, StreamObserver<ProductServiceOuterClass.ProductsPendingResponse> responseObserver) {
        try {
            pendingService.makeUserPending(request.getUserId());
            productService.checkUserProductOngoing(request.getUserId());
            List<Long> productList = productService.makeProductPending(request.getUserId());

            responseObserver.onNext(ProductServiceOuterClass.ProductsPendingResponse.newBuilder()
                    .setResult(ProductServiceOuterClass.ProductsPendingResponse.Result.OK)
                    .addAllProductId(productList).build());
        } catch (OngoingProductPresentException e) {
            List<Long> conflictProductList = productService.getConflictProducts(request.getUserId());

            responseObserver.onNext(ProductServiceOuterClass.ProductsPendingResponse.newBuilder()
                    .setResult(ProductServiceOuterClass.ProductsPendingResponse.Result.CONFLICT)
                    .addAllProductId(conflictProductList).build());
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

    @Override
    public void undoUserProductsPending(ProductServiceOuterClass.ProductsPendingRequest request, StreamObserver<ProductServiceOuterClass.ProductsPendingResponse> responseObserver) {
        try {
            pendingService.makeUserPending(request.getUserId());
            List<Long> productList = productService.releaseProductPending(request.getUserId());

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
    public void getProductDetail(ProductServiceOuterClass.ProductDetailRequest request, StreamObserver<ProductServiceOuterClass.ProductDetailResponse> responseObserver) {
        try {
            ProductDetailResDTO productDetailResDTO = biddingService.getProductDetail(request.getProductId());

            responseObserver.onNext(ProductServiceOuterClass.ProductDetailResponse.newBuilder()
                    .setStatus(ProductServiceOuterClass.ProductDetailResponse.Status.OK)
                    .setStartingPrice(productDetailResDTO.getStartingPrice())
                    .setStartingDatetime(Timestamp.newBuilder().setSeconds(productDetailResDTO.getStartingDatetime().getTime() * 1000).build())
                    .setEndingDatetime(Timestamp.newBuilder().setSeconds(productDetailResDTO.getEndingDatetime().getTime() * 1000).build()).build());
        } catch (ProductBadRequestException e) {
            responseObserver.onNext(ProductServiceOuterClass.ProductDetailResponse.newBuilder()
                    .setStatus(ProductServiceOuterClass.ProductDetailResponse.Status.INVALID).build());
        }
    }
}
