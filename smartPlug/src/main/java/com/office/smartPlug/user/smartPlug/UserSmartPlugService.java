package com.office.smartPlug.user.smartPlug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.office.smartPlug.user.smartPlug.page.Criteria;
import com.office.smartPlug.user.smartPlug.page.PageMakerVo;
import com.office.smartPlug.user.member.UserMemberVo;

@Service
public class UserSmartPlugService {

	@Autowired
	UserSmartPlugDao userSmartPlugDao;
	/*
	public int smartPlug_write_confirm(UserSmartPlugVo userSmartPlugVo) {
		System.out.println("[UserSmartPlugService] smartPlug_write_confirm() CALLED!!");
		
		int result = userSmartPlugDao.smartPlug_write_confirm(userSmartPlugVo);
		
		if (result <= 0)
			System.out.println("[UserSmartPlugService] smartPlug_write FAILED!!");
		else
			System.out.println("[UserSmartPlugService] smartPlug_write SUCCESS!!");
		
		return result;
	}
*/
	public Map<String, Object> getSmartPlugItems(Map<String, String> msgMap) {
		System.out.println("[UserSmartPlugService] getSmartPlugItems() CALLED!!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Criteria criteria = new Criteria(
										Integer.parseInt(msgMap.get("pageNum")), 
										Integer.parseInt(msgMap.get("amount"))
										);
		
		List<UserSmartPlugVo> userSmartPlugVos = userSmartPlugDao.getSmartPlugItems(msgMap, criteria);
		
		System.out.println("---------------> " + userSmartPlugVos.size());
		System.out.println("---------------> " + msgMap);
		
		int totalCnt = userSmartPlugDao.getTotalCnt(msgMap);
		PageMakerVo pageMakerVo = new PageMakerVo(criteria, totalCnt);
		
		map.put("userSmartPlugVos", userSmartPlugVos);
		map.put("pageMakerVo", pageMakerVo);
		
		return map;
		
	}
}
/*
	public Map<String, Object> deleteSmartPlugItem(Map<String, String> msgMap) {
		System.out.println("[UserSmartPlugService] deleteSmartPlugItem() CALLED!!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int result = userSmartPlugDao.deleteSmartPlugItem(msgMap);
		map.put("result", result);
		
		return map;
	}
	
	public UserSmartPlugVo smartPlug_modify(UserSmartPlugVo userSmartPlugVo) {
		System.out.println("[UserSmartPlugService] smartPlug_modify() CALLED!!");
		
		userSmartPlugVo = userSmartPlugDao.smartPlug_modify(userSmartPlugVo);
		
		return userSmartPlugVo;
	}
	
	
	public int smartPlug_modify_confirm(UserSmartPlugVo userSmartPlugVo) {
		System.out.println("[UserSmartPlugService] smartPlug_modify_confirm() CALLED!!");
		
		int result = userSmartPlugDao.pw_smartPlug_modify_confirm(userSmartPlugVo);
		
		return result;
	}
	
	public Map<String, Object> getSmartPlugItem(Map<String, String> msgMap) {
		System.out.println("[UserSmartPlugService] getSmartPlugItem() CALLED!!");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		UserSmartPlugVo userSmartPlugVo = userSmartPlugDao.getSmartPlugItem(msgMap);
		
		map.put("userSmartPlugVo", userSmartPlugVo);
		
		return map;
	}

}
	*/