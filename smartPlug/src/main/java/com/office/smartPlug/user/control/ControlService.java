package com.office.smartPlug.user.control;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ControlService {

	@Autowired
	ControlDao controlDao;
	
	public int sendData(int data) {
		System.out.println("[ControlService] sendData()");
		
		int result = controlDao.sendData(data);
		
		System.out.println("[ControlService] result ------------> " + result);
		
		return result;
	}

}
