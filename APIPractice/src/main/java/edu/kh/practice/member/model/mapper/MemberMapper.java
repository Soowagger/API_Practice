package edu.kh.practice.member.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.practice.member.model.dto.Member;

@Mapper
public interface MemberMapper {

	List<Member> selectList();

	Member login(String memberId);

	
	/** 회원가입 - 아이디 중복 체크 SQL 실행
	 * @param memberId
	 * @return
	 */
	int checkId(String memberId);
	
	/** 회원가입 - 이메일 중복체크
	 * @param memberEmail
	 * @return
	 */
	int checkEmail(String memberEmail);

	/** 회원가입 - 닉네임 중복체크
	 * @param memberNickname
	 * @return
	 */
	int checkNickname(String memberNickname);

	/** 회원가입(제출)
	 * @param inputMember
	 * @return
	 */
	int signup(Member inputMember);

	/** 네이버 로그인 - 이메일 중복 찾기
	 * @param naverEmail
	 * @return
	 */
	Member findMemberByEmail(String naverEmail);

	/** 네이버 가입
	 * @param naverNewMember
	 * @return
	 */
	int naverSignup(Member naverNewMember);



}
