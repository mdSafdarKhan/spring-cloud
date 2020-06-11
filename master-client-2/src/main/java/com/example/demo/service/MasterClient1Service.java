package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "master-client-1")
public interface MasterClient1Service {

	@GetMapping("/test")
	public String test();
}
