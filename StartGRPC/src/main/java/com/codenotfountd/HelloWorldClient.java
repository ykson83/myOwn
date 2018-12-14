package com.codenotfountd;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.codenotfound.grpc.helloworld.Greeting;
import com.codenotfound.grpc.helloworld.HelloWorldServiceGrpc;
import com.codenotfound.grpc.helloworld.Person;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class HelloWorldClient {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;
	
	@PostConstruct
	private void init() {
		 ManagedChannel managedChannel = ManagedChannelBuilder
		 	        .forAddress("localhost", 6565).usePlaintext().build();
         
	     helloWorldServiceBlockingStub =
	        HelloWorldServiceGrpc.newBlockingStub(managedChannel);
	}
	
	public String sayHello(String firstName, String lastName) {
	    Person person = Person.newBuilder().setFirstName(firstName)
	        .setLastName(lastName).build();
	    log.info("client sending {}", person);

	    Greeting greeting =
	        helloWorldServiceBlockingStub.sayHello(person);
	    log.info("client received {}", greeting);

	    return greeting.getMessage();
	  }

}
