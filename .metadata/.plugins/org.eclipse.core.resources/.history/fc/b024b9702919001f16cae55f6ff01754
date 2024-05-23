package edu.kh.practice.member.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.practice.member.model.dto.Member;
import edu.kh.practice.member.model.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SessionAttributes("loginMember")
@Controller
@RequiredArgsConstructor
@RequestMapping("member")
@Slf4j
public class MemberController {

	private final MemberService service;
	
	@PostMapping("login")
	public String login(
					Member inputMember,
					Model model,
					@RequestParam(value="autoLogin", required = false) String autoLogin,
					HttpServletResponse resp,
					RedirectAttributes ra
					
					) {
		
		Member loginMember = service.login(inputMember);
		
		if(loginMember == null) {
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		
		if(loginMember != null) {
			model.addAttribute("loginMember", loginMember);
			
			log.info("loginMember : " + loginMember);
		}
		
		
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(SessionStatus status) {
		
		status.setComplete();
		
		return "redirect:/";
	}
	
	
	@GetMapping("signup")
	public String signup() {
		
		return "/member/signup";
	}
	
	
	/** 회원가입 - 아이디 중복 체크
	 * @param memberId
	 * @return
	 */
	@ResponseBody
	@GetMapping("checkId")
	public int checkId(@RequestParam("memberId") String memberId) {
		
		return service.checkId(memberId);
	}
	
	
	/** 회원가입 - 닉네임 중복 체크
	 * @param memberNickname
	 * @return
	 */
	@ResponseBody
	@GetMapping("checkNickname")
	public int checkNickname(@RequestParam("memberNickname") String memberNickname) {
		
		return service.checkNickname(memberNickname);
	}
	
	
	/** 회원가입 - 이메일 중복 체크
	 * @param memberEmail
	 * @return
	 */
	@ResponseBody
	@GetMapping("checkEmail")
	public int checkEmail(@RequestParam("memberEmail") String memberEmail) {
		
		return service.checkEmail(memberEmail);
	}
	
	/** 회원가입(제출)
	 * @param inputMember
	 * @param memberAddress
	 * @param ra
	 * @return
	 */
	@PostMapping("signup")
	public String signup(@ModelAttribute Member inputMember,
						@RequestParam("memberAddress") String[] memberAddress,
						RedirectAttributes ra) {
		
		
		// 회원가입 서비스 호출
		int result = service.signup(inputMember, memberAddress);
		
		String path = null;
		String message = null;
		
		if(result > 0) { // 성공 시
			message = inputMember.getMemberNickname() + "님의 가입을 환영합니다 :)";
			path = "/";
			
		} else { // 실패
			message = "회원가입 실패...";
			path = "signup";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:" + path;
	}
	
	
	
}
