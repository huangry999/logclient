// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: log_service.proto

package tech.hry.logclient.grpc;

/**
 * Protobuf type {@code SaveLogResponse}
 */
public  final class SaveLogResponse extends
    com.google.protobuf.GeneratedMessageLite<
        SaveLogResponse, SaveLogResponse.Builder> implements
    // @@protoc_insertion_point(message_implements:SaveLogResponse)
    SaveLogResponseOrBuilder {
  private SaveLogResponse() {
  }
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
  }

  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    memoizedSerializedSize = size;
    return size;
  }

  public static tech.hry.logclient.grpc.SaveLogResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static tech.hry.logclient.grpc.SaveLogResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static tech.hry.logclient.grpc.SaveLogResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static tech.hry.logclient.grpc.SaveLogResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static tech.hry.logclient.grpc.SaveLogResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static tech.hry.logclient.grpc.SaveLogResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static tech.hry.logclient.grpc.SaveLogResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static tech.hry.logclient.grpc.SaveLogResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static tech.hry.logclient.grpc.SaveLogResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static tech.hry.logclient.grpc.SaveLogResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(tech.hry.logclient.grpc.SaveLogResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  /**
   * Protobuf type {@code SaveLogResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        tech.hry.logclient.grpc.SaveLogResponse, Builder> implements
      // @@protoc_insertion_point(builder_implements:SaveLogResponse)
      tech.hry.logclient.grpc.SaveLogResponseOrBuilder {
    // Construct using tech.hry.logclient.SaveLogResponse.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    // @@protoc_insertion_point(builder_scope:SaveLogResponse)
  }
  protected final Object dynamicMethod(
      MethodToInvoke method,
      Object arg0, Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new tech.hry.logclient.grpc.SaveLogResponse();
      }
      case IS_INITIALIZED: {
        return DEFAULT_INSTANCE;
      }
      case MAKE_IMMUTABLE: {
        return null;
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case VISIT: {
        Visitor visitor = (Visitor) arg0;
        tech.hry.logclient.grpc.SaveLogResponse other = (tech.hry.logclient.grpc.SaveLogResponse) arg1;
        if (visitor == MergeFromVisitor
            .INSTANCE) {
        }
        return this;
      }
      case MERGE_FROM_STREAM: {
        com.google.protobuf.CodedInputStream input =
            (com.google.protobuf.CodedInputStream) arg0;
        com.google.protobuf.ExtensionRegistryLite extensionRegistry =
            (com.google.protobuf.ExtensionRegistryLite) arg1;
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              default: {
                if (!input.skipField(tag)) {
                  done = true;
                }
                break;
              }
            }
          }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw new RuntimeException(e.setUnfinishedMessage(this));
        } catch (java.io.IOException e) {
          throw new RuntimeException(
              new com.google.protobuf.InvalidProtocolBufferException(
                  e.getMessage()).setUnfinishedMessage(this));
        } finally {
        }
      }
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        if (PARSER == null) {    synchronized (tech.hry.logclient.grpc.SaveLogResponse.class) {
            if (PARSER == null) {
              PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            }
          }
        }
        return PARSER;
      }
    }
    throw new UnsupportedOperationException();
  }


  // @@protoc_insertion_point(class_scope:SaveLogResponse)
  private static final tech.hry.logclient.grpc.SaveLogResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new SaveLogResponse();
    DEFAULT_INSTANCE.makeImmutable();
  }

  public static tech.hry.logclient.grpc.SaveLogResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<SaveLogResponse> PARSER;

  public static com.google.protobuf.Parser<SaveLogResponse> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

