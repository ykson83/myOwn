package com.proto.prototest.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*import com.proto.prototest.TextMessage;
import com.proto.prototest.TextRequest;
import com.proto.prototest.TextResponse;
import com.proto.prototest.TextServiceGrpc;
import com.proto.prototest.TextServiceGrpc.TextServiceBlockingStub;
*/

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ProtoTestClient {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*public static void main(String[] args) {
		
		//1.creating a gRPC channel
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8888) //set host & port
				  .usePlaintext() //
				  .build();
		
		//2.create a synchronous stub
		TextServiceBlockingStub blockingStub = TextServiceGrpc.newBlockingStub(channel);
		System.out.println("gRPC client connected to gRPC server");
		
		TextMessage textMsg = TextMessage.newBuilder()
		.setText("World")
		.build();
		
		//3.prepare request
		TextRequest request = TextRequest.newBuilder()
		.setReqTextMsg(textMsg)
		.build();
		
		//4.call the protoTest method on stub
		TextResponse response = blockingStub.protoTest(request);
		System.out.println("server reponse: " + response.getResTextMsg().getText());
		
		//5. close the channel
		channel.shutdown();
		System.out.println("gRPC client shutdown");
	}*/
}
