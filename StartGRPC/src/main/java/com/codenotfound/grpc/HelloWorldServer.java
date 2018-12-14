package com.codenotfound.grpc;

import java.util.List;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class HelloWorldServer {
	Logger log = LoggerFactory.getLogger(this.getClass());
	/*public static void main(String[] args) throws IOException, InterruptedException {
		
		Server server = ServerBuilder.forPort(6565)     // listen on port 6565
                .addService(new HelloWorldServiceImpl())   // add service implementation
                .build();
        server.start();                                 // start server
        server.awaitTermination();
	}*/
	
	private final Server server;
    private final int port;

    public HelloWorldServer(int port, List<BindableService> services) {
        this.port = port;

        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(this.port);
        services.forEach(service -> serverBuilder.addService(service));
        this.server = serverBuilder.build();
    }

    public void start() throws IOException, InterruptedException {
        server.start();
        log.info("Server started, listening on " + port);
        this.blockUntilShutdown();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            HelloWorldServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    public void stop() {
        if (server != null) server.shutdown();
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) server.awaitTermination();
    }
}
