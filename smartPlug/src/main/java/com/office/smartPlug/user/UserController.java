package com.office.smartPlug.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
	public String home() {
		System.out.println("[UserController] home() CALLED!!");
		
		String nextPage = "user/home";
		
		return nextPage;
		
	}
	
}

