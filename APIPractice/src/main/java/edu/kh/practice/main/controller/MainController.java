package edu.kh.practice.main.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.practice.member.model.dto.Member;
import edu.kh.practice.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MemberService service;
	
	@RequestMapping("/")
	public String mainPage(Model model,
			 @SessionAttribute(name = "loginMember", required = false) Member loginMember,
             RedirectAttributes ra) {
		
//        // 세션에 로그인된 회원 정보가 있는지 확인
//        if (loginMember != null) {
//            // 폰 번호가 null인 경우
//            if (loginMember.getMemberTel() == null) {
//                // 리다이렉트할 때 추가 메시지 또는 속성이 필요한 경우 설정
//                ra.addFlashAttribute("message", "핸드폰 인증이 되어있지 않음 / 마이페이지로 갈래?");
//                
//                return "redirect:/myPage/myPage"; // 마이페이지로 리다이렉트
//            }
//        }
		
		
		
		List<Member> memberList = service.selectList();
		
		if(loginMember != null && loginMember.getMemberTel() == null) {
			
				
			model.addAttribute("tel", null);
			
		}
		

		
		
		model.addAttribute("memberList", memberList);
		
		
		
		
		
		return "common/main";
		
	}
}
