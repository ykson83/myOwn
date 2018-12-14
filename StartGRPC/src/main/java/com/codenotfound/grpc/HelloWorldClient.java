package com.codenotfound.grpc;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import com.codenotfound.grpc.helloworld.Greeting;
import com.codenotfound.grpc.helloworld.HelloWorldServiceGrpc;
import com.codenotfound.grpc.helloworld.Person;
import com.codenotfound.grpc.helloworld.HelloWorldServiceGrpc.HelloWorldServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.netty.util.internal.StringUtil;

@Component
@SpringBootApplication
public class HelloWorldClient {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;
	
	@PostConstruct
	private void init() {
		 ManagedChannel managedChannel = ManagedChannelBuilder
		 	        .forAddress("localhost", 6565).usePlaintext().build();
		 System.out.println("gRPC client connected to gRPC server");
	     helloWorldServiceBlockingStub =
	        HelloWorldServiceGrpc.newBlockingStub(managedChannel);
	}*/
	
	/*public String sayHello(String firstName, String lastName) {
		System.out.println("client sayHello");
	    Person person = Person.newBuilder().setFirstName(firstName)
	        .setLastName(lastName).build();
	    log.info("client sending {}", person);

	    Greeting greeting =
	        helloWorldServiceBlockingStub.sayHello(person);
	    log.info("client received {}", greeting);

	    if(!greeting.getMessage().isEmpty()) System.out.println("!null: " + greeting.getMessage());
	    return greeting.getMessage();
	}*/
	
	public static void main(String[] args) {
		System.out.println("dd?");
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565) //set host & port
				  .usePlaintext() //
				  .build();
		
		//2.create a synchronous stub
		HelloWorldServiceBlockingStub blockingStub = HelloWorldServiceGrpc.newBlockingStub(channel);
		System.out.println("gRPC client connected to gRPC server");
		
		HelloWorldClient client = new HelloWorldClient();
//		client.sayHello("YeonKyung", "Son");
		
		String firstName = "YeonKyung";
		String lastName = "Son";
		
		Person person = Person.newBuilder().setFirstName(firstName)
		        .setLastName(lastName).build();
		System.out.println("client sending: " + person);
		
		Greeting greeting = blockingStub.sayHello(person);
		System.out.println("client received greeting: " + greeting);
		
		//5. close the channel
		channel.shutdown();
		System.out.println("gRPC client shutdown");
	}
}
