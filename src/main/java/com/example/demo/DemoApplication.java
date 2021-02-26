package com.example.demo;

import com.example.demo.api.GetOutNumberApi;
import com.example.demo.init.DemoInit;
import com.example.demo.mobile.MobileNumberUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(DemoApplication.class, args);
		//MobileNumberUtils.init();
	}

}
