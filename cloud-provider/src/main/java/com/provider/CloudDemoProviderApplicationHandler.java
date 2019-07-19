package com.provider;

import org.springframework.http.MediaType;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;

import com.provider.reactive.handler.StudentHandler;

import reactor.ipc.netty.http.server.HttpServer;


import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;
/**
 * 函数式编程方式
 * @author Administrator
 *
 */
public class CloudDemoProviderApplicationHandler {

	public static void main(String[] args) throws Exception {
		HttpHandler httpHandler = toHttpHandler(
                route(POST("/selectStudent").and(accept(MediaType.APPLICATION_JSON_UTF8)), StudentHandler::selectStudent).
                        and(route(GET("/saveStudent"), StudentHandler::insertStudent)));
        ReactorHttpHandlerAdapter httpHandlerAdapter = new ReactorHttpHandlerAdapter(httpHandler);
        HttpServer.create("localhost", 8080).newHandler(httpHandlerAdapter).block();
        System.in.read();
	}
}
