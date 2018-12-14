package com.codenotfound.grpc;

import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codenotfound.grpc.helloworld.Greeting;
import com.codenotfound.grpc.helloworld.HelloWorldServiceGrpc.HelloWorldServiceImplBase;
import com.codenotfound.grpc.helloworld.Person;

import io.grpc.stub.StreamObserver;

@GRpcService
//spring boot 실행될 때 GRpcService 어노테이션을 읽어서 서버 올려 
public class HelloWorldServiceImpl extends HelloWorldServiceImplBase {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void sayHello(Person request, StreamObserver<Greeting> responseObserver) {
		
		log.debug("server received {}", request);

	    String message = "Hello " + request.getFirstName() + " "
	        + request.getLastName() + "!";
	    Greeting greeting =
	        Greeting.newBuilder().setMessage(message).build();
	    
	    log.debug("server responded {}", greeting);

	    responseObserver.onNext(greeting);
	    responseObserver.onCompleted();

	}
}
