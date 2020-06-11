package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class MyController {

	private static Logger log = LoggerFactory.getLogger(MyController.class);
	
	@Value("${prop1}")
	private String prop1;
	
	@GetMapping("/test")
	public String test() {
		log.info("prop1: " + prop1);
		return prop1;
	}
	
	@GetMapping("/testHystrix")
	@HystrixCommand(fallbackMethod = "fallback", 
					commandProperties = 
						@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"))
	public String testHystrix() throws InterruptedException {
		Thread.sleep(5000);
		return "from testHystrix method";
	}
	
	public String fallback() {
		return "from fallback method";
	}
}
