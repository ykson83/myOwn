package com.codenotfound.grpc;

import java.util.Arrays;
import java.util.List;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;


//얘는 spring boot 안타고 java로만 서버 올릴때 필요한 코드야
//spring boot 타서 올릴땐 아예 삭제해버려도 돼
public class HelloWorldServer {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
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
//        this.server.awaitTermination();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            HelloWorldServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    public void stop() {
        if (server != null) server.shutdown();
        System.out.println("server stoped");
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) server.awaitTermination();
    }
    
    public static void main(String[] args) throws InterruptedException, IOException {
		
    	HelloWorldServer server = new HelloWorldServer(6565, Arrays.asList(new HelloWorldServiceImpl()));
		server.start();
		server.blockUntilShutdown();
	}
}
