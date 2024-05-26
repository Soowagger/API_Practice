package edu.kh.practice.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("myPage")
public class MyPageController {
	
	@GetMapping("myPage")
	public String phonNull() {
		
		return "myPage/myPage";
	}
}
