package com.codenotfound.grpc;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.codenotfound.grpc.helloworld.Greeting;
import com.codenotfound.grpc.helloworld.HelloWorldServiceGrpc;
import com.codenotfound.grpc.helloworld.Person;
import com.codenotfound.grpc.helloworld.HelloWorldServiceGrpc.HelloWorldServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class HelloWorldClient {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("dd?");
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 6566) //set host & port
				  .usePlaintext() //
				  .build();
		
		//2.create a synchronous stub
		HelloWorldServiceBlockingStub blockingStub = HelloWorldServiceGrpc.newBlockingStub(channel);
		System.out.println("gRPC client connected to gRPC server");
		
		HelloWorldClient client = new HelloWorldClient();
		
		String firstName = "YeonKyung";
		String lastName = "Son";
		
		Person person = Person.newBuilder().setFirstName(firstName)
		        .setLastName(lastName).build();
		System.out.println("client sending: " + person);
		
		Greeting greeting = blockingStub.sayHello(person);
		System.out.println("client received greeting: " + greeting);
		
		//5. close the channel
		//channel.shutdown();  
		channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
		//channel.shutdown() 바로하면 연결 끊기면서 exception 발생
		//"IOException: 현재 연결은 원격 호스트에 의해 강제로 끊겼습니다"
		//server는 안죽지만 client는 죽나봐
		
		System.out.println("gRPC client shutdown");
	}
}
