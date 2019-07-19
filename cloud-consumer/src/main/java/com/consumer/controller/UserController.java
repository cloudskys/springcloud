package com.consumer.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.consumer.domain.User;

import reactor.core.Reactor;
import reactor.event.Event;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
    private RestTemplate restTemplate;
	@GetMapping("/getUser/{id}")
    public User getUser(@PathVariable Long id){
    	return restTemplate.getForObject("http://localhost:8078/user/getUser/"+id,User.class);
    }
	@Autowired
	@Qualifier("pathReactor")//同样指定并注入
	Reactor r;
	
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@RequestMapping("getUserInfo")
	public void getUserInfo(HttpServletResponse response, String address) {
		/**
		 * 通过notify方法发送，Event.wrap(address)可以为空也可以增加replyToKey参数但是具体的用法我还没有深入研究
		 * 还有就是这个调用方法的key不能为空，会抛异常一会给大家演示
		 */
		User u = new User();
		u.setName("王伟");
		r.notify("getUserMessage", Event.wrap(u));
		//ResponseUtil.renderSuccessJson(response,"success","验证码邮件已发送，请注意查收！"+date.format(new Date()));
		System.out.println("验证码邮件已发送，请注意查收！"+date.format(new Date()));;
	}
	@RequestMapping("resultMessage")
	public String result(HttpServletResponse response, Integer id) {
		r.notify("resultMessage", Event.wrap(id));
		//ResponseUtil.renderSuccessJson(response,"success","返回时间："+date.format(new Date()));
		return ("返回时间："+date.format(new Date()));
	}
}
