package edu.kh.practice.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.practice.member.model.dto.Member;
import edu.kh.practice.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@PropertySource("classpath:/config.properties")
@Slf4j
public class MainController {
	
	@Value("${spring.naver.login.client.id}")
	private String clientId;
	
	private final MemberService service;
	
	@RequestMapping("/")
	public String mainPage(Model model,
			 @SessionAttribute(name = "loginMember", required = false) Member loginMember,
             RedirectAttributes ra) {
		
		
		
		List<Member> memberList = service.selectList();
		
		if(loginMember != null && loginMember.getMemberTel() == null) {
			
				
			model.addAttribute("tel", null);
			
		}
		
		
		model.addAttribute("memberList", memberList);
		
		model.addAttribute("clientId", clientId);
		
		
		return "common/main";
		
	}
}
