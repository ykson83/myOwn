package com.proto.prototest.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*import com.proto.prototest.TextMessage;
import com.proto.prototest.TextRequest;
import com.proto.prototest.TextResponse;
import com.proto.prototest.TextServiceGrpc.TextServiceImplBase;*/

import io.grpc.stub.StreamObserver;

@Component
public class ProtoTestServiceImpl 
//extends TextServiceImplBase 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	public ProtoTestServiceImpl() {}
	
	/*@Override
	public void protoTest(TextRequest request, StreamObserver<TextResponse> responseObserver) {
		//StreamObserver가 머까..?
		log.debug("start service");
		
		TextMessage reqTextMsg = request.getReqTextMsg();
		System.out.println("Text from client : " + reqTextMsg.getText());
		
		TextMessage resTextMsg = TextMessage.newBuilder()
											.setText("Hello " + reqTextMsg.getText())
											.build();

		TextResponse response = TextResponse.newBuilder()
											.setResTextMsg(resTextMsg)
											.build();

		//responseObserver send a single response back
		responseObserver.onNext(response);
		//must call onCompleted
		responseObserver.onCompleted();
	}*/
}
