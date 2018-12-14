package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("api/test")
public class TestController {

//	@GetMapping(value = "/getTest")
	@RequestMapping("/test")
	public String test() {
		System.out.println("testController");
		return "test";
	}
}
