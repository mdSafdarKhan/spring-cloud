package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.service.MasterClient1Service;

@RestController
public class MyController {

	private static Logger log = LoggerFactory.getLogger(MyController.class);
	
	@Autowired
	private MasterClient1Service masterClient1Service;
	
	@GetMapping("/test")
	public String test() {
		RestTemplate restTemplate = new RestTemplate();
		String masterClient1 = restTemplate.getForObject("http://LP-109181.igglobal.com:8080/test", String.class);
		String finalMessage = "master-client-2" + ", " + masterClient1;
		log.info("final message: " + finalMessage);
		return finalMessage;
	}
	
	@GetMapping("/testWithFeignClient")
	public String testWithFeignClient() {
		return masterClient1Service.test();
	}
}
