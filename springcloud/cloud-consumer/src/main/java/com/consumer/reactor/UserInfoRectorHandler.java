package com.consumer.reactor;

import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.consumer.domain.User;

import reactor.core.Reactor;
import reactor.event.Event;
import reactor.spring.annotation.Selector;
@Component
public class UserInfoRectorHandler {
 
	@Autowired
	@Qualifier("pathReactor")//通过name指定刚定义的库，并注入进来
	Reactor r;
	
	//@Autowired
	//PushMailUtils pushMailUtils;//这是我封装的发送邮件的类，不用管
	
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 具体执行异步操作的方法，注意方法上的注解value中的名字要和一会调用这个方法的key一至
	 * reactor中的则是指定刚才的库名，你也可以指定其他的但是前提得先创建
	 * @param e
	 * @throws InterruptedException
	 */
	@Selector(value = "getUserMessage", reactor = "@pathReactor")
	public void getUserMessage(Event<User> e) throws InterruptedException {
		/*
		 * 获取传入的参数-可以是：String,Integer,Map及model层实体类，目前我只实验了这些
		 * 下面的就好理解了，我就不多说了
		 */
		User mailAddress = e.getData();
		//Thread.sleep(2000);
		//boolean result = pushMailUtils.pushMail(mailAddress, "注册验证", "欢迎您使用xxx，您本次的验证码为：123456 请前往注册页面完成注册");
		boolean result = true;
		if(result) {
			System.out.println("mailAddress:"+mailAddress.getName()+">>>>>>>>>>>>>>>邮件已成功发送"+date.format(new Date()));
		}
	}
	@Selector(value = "resultMessage", reactor = "@pathReactor")
	public void resultInt(Event<Integer> e) throws InterruptedException {
		for(int i = 0; i < e.getData(); i++) {
			Thread.sleep(1);
			System.out.println(i+"打印时间："+date.format(new Date()));
		}
	}
}