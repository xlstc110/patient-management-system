package com.pm.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import com.google.api.Billing;
import com.google.rpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class BillingGrpcService extends BillingServiceImplBase {

    @Override
    // override the createBillingAccount method from Maven generated BillingServiceImplBase class
    // since it is the default method from proto file structure
    public void createBillingAccount(billing.BillingRequest billingRequest,
                                     StreamObserver<billing.BillingResponse> responseObserver) {
        // StreamObserver allows sending multiple responses back to a client
        // create a continued stream and accept new real-time communication back and forth from the client.
        // Example: a Chat Application, which is different from REST communication (Single Communication)

        log.info("createBillingAccount request received : {}", billingRequest);

        // Business logic - save to database, perform calculates etc...

        // generate gRPC response
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("12345")
                .setStatus("Active")
                .build();

        // send the response back to the client
        responseObserver.onNext(response);

        // operation is done, close the RPC communication cycle
        responseObserver.onCompleted();

    }
}
