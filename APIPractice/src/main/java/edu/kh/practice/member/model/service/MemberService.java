package edu.kh.practice.member.model.service;

import java.util.List;

import edu.kh.practice.member.model.dto.Member;

public interface MemberService {

	List<Member> selectList();

	Member login(Member inputMember);

	
	/** 회원가입 - 아이디 중복 체크
	 * @param memberId
	 * @return
	 */
	int checkId(String memberId);
	
	
	/** 회원가입 - 이메일 중복 체크
	 * @param memberEmail
	 * @return
	 */
	int checkEmail(String memberEmail);

	/** 회원가입 - 닉네임 중복 체크
	 * @param memberNickname
	 * @return
	 */
	int checkNickname(String memberNickname);
	
	
	/** 회원가입(제출)
	 * @param inputMember
	 * @param memberAddress
	 * @return
	 */
	int signup(Member inputMember, String[] memberAddress);

	/** 네이버 회원 가입 
	 * @param newMember
	 * @return
	 */
	int naverSignup(Member naverNewMember);

	

}
