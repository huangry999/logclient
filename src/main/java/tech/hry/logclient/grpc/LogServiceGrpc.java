package tech.hry.logclient.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: log_service.proto")
public final class LogServiceGrpc {

  private LogServiceGrpc() {}

  public static final String SERVICE_NAME = "LogService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<tech.hry.logclient.grpc.SaveLogRequest,
      tech.hry.logclient.grpc.SaveLogResponse> getStoreMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Store",
      requestType = tech.hry.logclient.grpc.SaveLogRequest.class,
      responseType = tech.hry.logclient.grpc.SaveLogResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<tech.hry.logclient.grpc.SaveLogRequest,
      tech.hry.logclient.grpc.SaveLogResponse> getStoreMethod() {
    io.grpc.MethodDescriptor<tech.hry.logclient.grpc.SaveLogRequest, tech.hry.logclient.grpc.SaveLogResponse> getStoreMethod;
    if ((getStoreMethod = LogServiceGrpc.getStoreMethod) == null) {
      synchronized (LogServiceGrpc.class) {
        if ((getStoreMethod = LogServiceGrpc.getStoreMethod) == null) {
          LogServiceGrpc.getStoreMethod = getStoreMethod =
              io.grpc.MethodDescriptor.<tech.hry.logclient.grpc.SaveLogRequest, tech.hry.logclient.grpc.SaveLogResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Store"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tech.hry.logclient.grpc.SaveLogRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  tech.hry.logclient.grpc.SaveLogResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LogServiceMethodDescriptorSupplier("Store"))
              .build();
        }
      }
    }
    return getStoreMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LogServiceStub newStub(io.grpc.Channel channel) {
    return new LogServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LogServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new LogServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LogServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new LogServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class LogServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void store(tech.hry.logclient.grpc.SaveLogRequest request,
        io.grpc.stub.StreamObserver<tech.hry.logclient.grpc.SaveLogResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getStoreMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStoreMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                tech.hry.logclient.grpc.SaveLogRequest,
                tech.hry.logclient.grpc.SaveLogResponse>(
                  this, METHODID_STORE)))
          .build();
    }
  }

  /**
   */
  public static final class LogServiceStub extends io.grpc.stub.AbstractStub<LogServiceStub> {
    private LogServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogServiceStub(channel, callOptions);
    }

    /**
     */
    public void store(tech.hry.logclient.grpc.SaveLogRequest request,
        io.grpc.stub.StreamObserver<tech.hry.logclient.grpc.SaveLogResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStoreMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class LogServiceBlockingStub extends io.grpc.stub.AbstractStub<LogServiceBlockingStub> {
    private LogServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public tech.hry.logclient.grpc.SaveLogResponse store(tech.hry.logclient.grpc.SaveLogRequest request) {
      return blockingUnaryCall(
          getChannel(), getStoreMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class LogServiceFutureStub extends io.grpc.stub.AbstractStub<LogServiceFutureStub> {
    private LogServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private LogServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LogServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new LogServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<tech.hry.logclient.grpc.SaveLogResponse> store(
        tech.hry.logclient.grpc.SaveLogRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getStoreMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STORE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final LogServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(LogServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STORE:
          serviceImpl.store((tech.hry.logclient.grpc.SaveLogRequest) request,
              (io.grpc.stub.StreamObserver<tech.hry.logclient.grpc.SaveLogResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class LogServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LogServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return tech.hry.logclient.grpc.LogServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LogService");
    }
  }

  private static final class LogServiceFileDescriptorSupplier
      extends LogServiceBaseDescriptorSupplier {
    LogServiceFileDescriptorSupplier() {}
  }

  private static final class LogServiceMethodDescriptorSupplier
      extends LogServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    LogServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (LogServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LogServiceFileDescriptorSupplier())
              .addMethod(getStoreMethod())
              .build();
        }
      }
    }
    return result;
  }
}
