package com.example.demo;

import com.example.demo.mobile.MobileNumberUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		Jedis jedis = new Jedis("localhost");
//		System.out.println(jedis.getClient().getPort());
//		System.out.println("连接本地的Redis服务器成功");
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext-redis.xml");
//		//从容器中获得JedisClient对象
//		JedisClient jedisClient = applicationContext.getBean(JedisClient.class);
//		jedisClient.set("first", "100");
//		String result = jedisClient.get("first");
//		System.out.println("*************************"+result);
//		//查看服务是否运行
//		System.out.println("服务正在运行：" + jedis.ping());
	}

}
