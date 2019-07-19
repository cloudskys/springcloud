package com.provider;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

import com.provider.reactive.WebFluxConfig;

import reactor.ipc.netty.http.server.HttpServer;

/**
 * 基于Reactor Netty实现WebFlux服务
 * 
 * @author wangyt
 * @date 2019/7/19
 **/
// @SpringBootApplication
public class CloudDemoProviderApplication {

	public static void main(String[] args) throws Exception {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				WebFluxConfig.class);
		// 通过ApplicationContext创建HttpHandler
		HttpHandler httpHandler = WebHttpHandlerBuilder.applicationContext(applicationContext).build();
		ReactorHttpHandlerAdapter httpHandlerAdapter = new ReactorHttpHandlerAdapter(httpHandler);
		HttpServer.create("localhost", 8080).newHandler(httpHandlerAdapter).block();
		System.in.read();
	}
}
