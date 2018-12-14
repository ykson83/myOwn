package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.codenotfound.grpc")
//server 패키지를 다르게 해놔서 이렇게 지정해줘야해
public class StartGrpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartGrpcApplication.class, args);
	}
	
	//pom.xml
	//1.grpc-spring-boot-starter를 쓰면 
	//스프링부트 실행시 위에 지정한 패키지를 스캔하면서 @GRpcService를 찾아서 서버를 올려
	//2.grpc-all을 쓸 경우  HelloWorldServer코드를 실행해서 내가 서버를 올려야해(그럼 스프링 부트를 안타는 거여)
	
	//적용
	//server 역할을 하는 애 pom.xml에서 groupId, artifactId, version 따서 client가 있는 프젝 pom.xml에 넣어줘
	//<dependency>
    //	<groupId>son.yeon.kyung</groupId>
	//	<artifactId>StartGRPC</artifactId>
	//	<version>0.0.1-SNAPSHOT</version>
	//</dependency> 
}

