package edu.kh.practice.member.controller;

import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.practice.member.model.dto.Member;
import edu.kh.practice.member.model.mapper.MemberMapper;
import edu.kh.practice.member.model.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@SessionAttributes("loginMember")
@RequiredArgsConstructor
@Controller
@Slf4j
@PropertySource("classpath:/config.properties")
public class NaverLoginController {
	
	@Value("${spring.naver.login.client.id}")
	private String clientId;
	
	
	private final MemberMapper mapper; 
	private final MemberService service; 
	
	
	@GetMapping("/naverLogin")
	public String naverCallback(Model model) {
		
		model.addAttribute("clientId", clientId);
		
		
		return "member/naverLogin";
	}
	
	@PostMapping("/naverLoginData")
	@ResponseBody
	public int naverLogin(
					Model model,
					RedirectAttributes ra,
					@RequestBody Map<String, String> map,
					HttpSession session
					) {
		
		String naverId = map.get("id"); // 식별키로 들어옴
		String naverEmail = map.get("email");
		String naverNickname = map.get("nickname");
		
		log.info("naverId : {}", naverId);
		log.info("naverEmail : {}", naverEmail);
		
		Member member = mapper.findMemberByEmail(naverEmail);
		
		if(member != null) {
			log.info("멤버 정보 체크 : {}", member);
			
			// 네이버로 가입한 유저인지 확인
			if(member.getSocialLoginType().equals("N")) {
				
				session.setAttribute("loginMember", member);
				
								
				return 2;
				
			} else {
				
				// 일반 가입 혹은 다른 방법으로 가입된 이메일
				return 1;
			} 
			
		} else { // 이메일 중복 X (신규 가입 회원)
			
	        String dummyPassword = "naver" + RandomStringUtils.randomAlphanumeric(10); // 임의의 더미 패스워드 생성
	        
	        log.info(dummyPassword);
	        
	        Member naverNewMember = Member.builder()
	                                  .memberEmail(naverEmail)
	                                  .memberNickname(naverNickname)
	                                  .memberPw(naverId) // 식별 키
	                                  .memberAddress(null)
	                                  .socialLoginType("N") // 네이버면 N
	                                  .memberId(dummyPassword) // 네이버 + 난수
	                                  .build();
	        
	        
	        
	        int result = service.naverSignup(naverNewMember);
	        
	        if(result > 0) {
	            log.info("회원가입 성공 : {}", naverNewMember);
	            
	            session.setAttribute("loginMember", naverNewMember);
	            
	           	            
	            return 3; // 회원가입 성공
	            
	        } else {
	            return 4; // 오류 발생
	        }
			
		}
		
	}
	
}
