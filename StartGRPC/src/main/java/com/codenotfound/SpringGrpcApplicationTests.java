package com.codenotfound;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import com.codenotfound.grpc.HelloWorldClient;
import com.codenotfound.grpc.HelloWorldServer;
import com.codenotfound.grpc.HelloWorldServiceImpl;
import com.codenotfound.grpc.helloworld.HelloWorldServiceGrpc;
import com.codenotfound.grpc.helloworld.HelloWorldServiceGrpc.HelloWorldServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@Component
@SpringBootApplication
public class SpringGrpcApplicationTests {
	
//	@Autowired
	/* private HelloWorldClient helloWorldClient;

	  @Test
	  public void testSayHello() {
	    assertThat(helloWorldClient.sayHello("John", "Doe"))
	        .isEqualTo("Hello John Doe!");
	  }*/
	
	/*public static void main(String[] args) throws IOException, InterruptedException {
		System.out.println("dd?");
		HelloWorldServer server = new HelloWorldServer(6565, Arrays.asList(new HelloWorldServiceImpl()));
		server.start();
		server.blockUntilShutdown();
		
		
		
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565) //set host & port
				  .usePlaintext() //
				  .build();
		
		//2.create a synchronous stub
		HelloWorldServiceBlockingStub blockingStub = HelloWorldServiceGrpc.newBlockingStub(channel);
		System.out.println("gRPC client connected to gRPC server");
		
		HelloWorldClient client = new HelloWorldClient();
		client.sayHello("YeonKyung", "Son");
//		System.out.println("?: " + client.sayHello("Son", "YeonKyung"));
		
		//5. close the channel
		channel.shutdown();
//		server.blockUntilShutdown();
		System.out.println("gRPC client shutdown");
		
	}*/
}
