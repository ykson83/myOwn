package com.codenotfound.grpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.codenotfound.grpc.helloworld.Greeting;
import com.codenotfound.grpc.helloworld.HelloWorldServiceGrpc.HelloWorldServiceImplBase;
import com.codenotfound.grpc.helloworld.Person;

import io.grpc.stub.StreamObserver;

@Component
public class HelloWorldServiceImpl extends HelloWorldServiceImplBase{
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
