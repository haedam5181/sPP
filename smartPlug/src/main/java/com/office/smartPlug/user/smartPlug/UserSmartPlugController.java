package com.office.smartPlug.user.smartPlug;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.office.smartPlug.user.member.UserMemberVo;

@Controller
@RequestMapping("/user/smartPlug")
public class UserSmartPlugController {

	@Autowired
	UserSmartPlugService userSmartPlugService;
	
	@RequestMapping(value = "/smartPlug_list", method = RequestMethod.GET)
	public String smartPlug_list(HttpSession session) {
		System.out.println("[UserSmartPlugController] smartPlug_list() CALLED!!");
		
		String nextPage = "user/smartPlug/smartPlug_list";
		
		if (session.getAttribute("logined_userMemberVo") == null)
			return "redirect:/";
			
		return nextPage;
		
	}
	
	@RequestMapping(value = "/smartPlug_write", method = RequestMethod.GET)
	public String smartPlug_write(HttpSession session) {
		System.out.println("[UserSmartPlugController] smartPlug_write() CALLED!!");
		
		String nextPage = "user/smartPlug/smartPlug_write";
		
		if (session.getAttribute("logined_userMemberVo") == null) 
			return "redirect:/";
		
		return nextPage;
		
	}
	
	/*
	@RequestMapping(value = "/smartPlug_write_confirm", method = RequestMethod.POST)
	public String smartPlug_write_confirm(HttpSession session, 
									  UserSmartPlugVo userSmartPlugVo, 
									  @RequestParam("d_img_file") MultipartFile d_img_file ) {
		System.out.println("[UserSmartPlugController] smartPlug_write_confirm() CALLED!!");
		
		String nextPage = "user/smartPlug/smartPlug_write_success";
		
		// 로그인 인증
		UserMemberVo logined_userMemberVo = 
				(UserMemberVo) session.getAttribute("logined_userMemberVo");
		if (logined_userMemberVo == null)
			return "redirec:/";
		
		// 로그인 계정 SET
		userSmartPlugVo.setT_id(logined_userMemberVo.getT_id());
		
		// file 처리(저장)
		String fileOriName = d_img_file.getOriginalFilename();		// 사용자가 업로드한 file 이름
		String uploadDir = "c:\\smartPlug\\upload\\" + userSmartPlugVo.getT_id();
		
		String fileExtension = 
				fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length()); // file 확장자
		
		// long fileSize = d_img_file.getSize();
		
		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replaceAll("-", "");
		
		File savefile = new File(uploadDir + "\\" + uniqueName + fileExtension);
		
		try {
			
			d_img_file.transferTo(savefile);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		// DB 작업
	
		userSmartPlugVo.setD_img_name(uniqueName + fileExtension);
		
		int result = userSmartPlugService.smartPlug_write_confirm(userSmartPlugVo);
		if (result <= 0)
			nextPage = "user/smartPlug/smartPlug_write_fail";
		
		return nextPage;
		
	}
	*/
	
	@RequestMapping(value = "/getSmartPlugItems", method = RequestMethod.POST)
	@ResponseBody
	public Object getSmartPlugItems(@RequestBody Map<String, String> msgMap) {
		System.out.println("[UserDiaryController] getDiaryItems() CALLED!!");
		
		Map<String, Object> map = userSmartPlugService.getSmartPlugItems(msgMap);
		
		return map;
	}
/*	
	@RequestMapping(value = "/deleteSmartPlugItem", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteSmartPlugItem(@RequestBody Map<String, String> msgMap) {
		System.out.println("[UserSmartPlugController] deleteSmartPlugItem() CALLED!!");
		
		Map<String, Object> map = userSmartPlugService.deleteSmartPlugItem(msgMap);
		
		return map;
		
	}
	*/
	/*
	@RequestMapping(value = "/smartPlug_modify", method = RequestMethod.GET)
	public String smartPlug_modify(HttpSession session, UserSmartPlugVo userSmartPlugVo, Model model) {
		System.out.println("[UserSmartPlugController] smartPlug_modify() CALLED!!");
		
		String nextPage = "user/smartPlug/smartPlug_modify";
		
		if (session.getAttribute("logined_userMemberVo") == null) 
			return "redirect:/";
		
		userSmartPlugVo = userSmartPlugService.smartPlug_modify(userSmartPlugVo);
		model.addAttribute("userSmartPlugVo", userSmartPlugVo);
		
		return nextPage;
		
	}
	*/
	/*
	@RequestMapping(value = "/smartPlug_modify_confirm", method = RequestMethod.POST)
	public String smartPlug_modify_confirm(HttpSession session, 
									   UserSmartPlugVo userSmartPlugVo, 
									   @RequestParam("d_img_file") MultipartFile d_img_file) {
		System.out.println("[UserSmartPlugController] smartPlug_modify_confirm() CALLED!!");
		
		String nextPage = "user/smartPlug/smartPlug_modify_success";
		
		// 로그인 인증
		UserMemberVo logined_userMemberVo = 
				(UserMemberVo) session.getAttribute("logined_userMemberVo");
		if (logined_userMemberVo == null)
			return "redirec:/";
		
		// 아이디(m_id) SET
		userSmartPlugVo.setT_id(logined_userMemberVo.getT_id());
		
		// file 처리(저장)
		String fileOriName = d_img_file.getOriginalFilename();		// 사용자가 업로드한 file 이름
		
		if (!fileOriName.equals("")) {
			//long fileSize = d_img_file.getSize();
			
			String uploadDir = "c:\\smartPlug\\upload\\" + userSmartPlugVo.getT_id();
			
			String fileExtension = 
					fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length()); // file 확장자
			
			UUID uuid = UUID.randomUUID();
			String uniqueName = uuid.toString().replaceAll("-", "");
			
			File savefile = new File(uploadDir + "\\" + uniqueName + fileExtension);
			
			try {
				
				d_img_file.transferTo(savefile);
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			userSmartPlugVo.setD_img_name(uniqueName + fileExtension);
			
		}
		
		int result = userSmartPlugService.smartPlug_modify_confirm(userSmartPlugVo);
		if (result <= 0)
			nextPage = "user/smartPlug/smartPlug_modify_fail";
		
		return nextPage;
		
	}
	*/
	/*
	@RequestMapping(value = "/getSmartPlugItem", method = RequestMethod.POST)
	@ResponseBody
	public Object getSmartPlugItem(@RequestBody Map<String, String> msgMap) {
		System.out.println("[UserSmartPlugController] getDiaryItem() CALLED!!");
		
		Map<String, Object> map = userSmartPlugService.getSmartPlugItem(msgMap);
		
		return map;
		
	}
	*/
	/*
	@RequestMapping(value = "/download_img", method = RequestMethod.GET)
	public void download_img(UserSmartPlugVo userSmartPlugVo, HttpServletResponse response) {
		System.out.println("[UserSmartPlugController] download_img() CALLED!!");
		
		System.out.println("id: " + userSmartPlugVo.getT_id());
		System.out.println("img_name: " + userSmartPlugVo.getD_img_name());
		
		FileInputStream fileInputStream = null;
		
		try {
			
			String imgPath = "C:\\smartPlug\\upload\\" + userSmartPlugVo.getT_id() + '\\' + userSmartPlugVo.getD_img_name();
			
			// 헤더 설정(for 다운로드)
			File file = new File(imgPath);
			response.setHeader(imgPath, imgPath);
			response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
			
			// 파일 읽기(input) & 쓰기(output): (file --> FileInputStream --> buffer --> OutputStream)
			fileInputStream = new FileInputStream(imgPath);
			OutputStream outputStream = response.getOutputStream();
			
			int read = 0;
			byte[] buffer = new byte[1024];
			while ((read = fileInputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, read);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if (fileInputStream != null)
						fileInputStream.close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				
			}
			
		}
		
	}
	*/
}