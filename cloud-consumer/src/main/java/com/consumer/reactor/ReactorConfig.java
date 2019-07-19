package com.consumer.reactor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;
 
@Configuration
@EnableReactor
public class ReactorConfig {
 
	@Bean
	Environment env() {
		return new Environment();
	}
	/**
	 * 创建一个异步执行库，其中可以有多个异步的方法
	 * 这些会在Handler.class中看到
	 * @param env-固定格式-像我这样写就行，其余的自己在研究吧
	 * @return
	 */
	@Bean(name = "pathReactor")
	public Reactor pathReactor(Environment env) {
		return Reactors.reactor().env(env).dispatcher(Environment.RING_BUFFER).get();
	}
}
