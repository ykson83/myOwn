package com.proto.prototest.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class ProtoTestServer {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/*public static void main(String[] args) throws IOException, InterruptedException {
		
		Server server = ServerBuilder.forPort(8888) //listen on port 8888
				                     .addService(new ProtoTestServiceImpl()) //add service impl..?느에?
				                     .build();

		server.start(); //start server
		System.out.println("gRPC server started");
					Runtime.getRuntime().addShutdownHook(new Thread( () -> {
			server.shutdown();
			System.out.println("gRPC server shutdown");
		} ));
					server.awaitTermination();
	}*/
	
	
	
	/*private final int port;
	private final Server server;
	
	public ProtoTestServer(int port, BindableService service) {
		this.port = port;
		this.server = ServerBuilder.forPort(port)
								   .addService(service)
								   .build();
	}
	
	public void start() throws Exception {
		this.server.start();
		log.debug("server가 " + port + " 포트에서 리스닝중");
		this.server.awaitTermination();
	}
	
	public void shutdown() {
		log.debug("서버 종료..");
		server.shutdown();
		log.debug("서버 종료 완료");
	}*/
	

	/*public static void main(String[] args) {
	
		Server server = ServerBuilder<ServerBuilder<T>>.forPort(8888)
				.addService(new ProtoTestServiceImpl())
				.build();

		server.start();
		System.out.println("gRPC server started");
		
		Runtime.getRuntime().addShutdownHook(new Thread( () -> {
		server.shutdown();
		System.out.println("gRPC server shutdown");
		} ));
		
		server.awaitTermination();
	}*/
}
