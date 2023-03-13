package com.office.smartPlug.user.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/control")
public class ControlController {

	@Autowired
	ControlService controlService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	public String control() {
		System.out.println("[ControlController] control()");

		String nextPage = "user/smartPlug/control";

		return nextPage;

	}

	@PostMapping("/sendData")
	@ResponseBody
	public Object sendData(@RequestParam("data") String data) {
		System.out.println("[ControlController] sendData()");

		int sendData = Integer.parseInt(data);
		System.out.println("sendData = " + sendData);
		
		int result = controlService.sendData(sendData);
		System.out.println("result = " + result);
		
		System.out.println("[ControlController] result---------------->" + result);
		
		return result;
	}

}
