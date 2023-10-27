package project.spring.calla.persistence;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.MemberVO;

@Repository
public class MemberDAOImple implements MemberDAO{


	private static final Logger logger =
			LoggerFactory.getLogger(MemberDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.MemberMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int checkId(String memberId) { // ���̵� �ߺ�üũ
		logger.info("select_by_id() ȣ��");
		int result  = sqlSession.selectOne(NAMESPACE + ".select_by_id", memberId);
		logger.info(result+"�ߺ�");
		return result;
	}

	@Override
	public int checkNickname(String memberNickname) { //
		logger.info("checkNickname() 호출");
		return sqlSession.selectOne(NAMESPACE + ".select_by_nickname", memberNickname);
	}

	@Override
	public int insert(MemberVO vo) { // ȸ������
		logger.info("insert() ȣ�� : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	
	public String login(String memberId, String memberPw) {
		logger.info("login() ȣ�� memberId = " + memberId + "memberPw = " + memberPw);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("memberId", memberId);
		args.put("memberPw", memberPw);
		return sqlSession.selectOne(NAMESPACE + ".login", args);
	}

	@Override
	public MemberVO select(String memberId) {
		logger.info("select(memberId) ȣ�� memberId : " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", memberId);
	}

	@Override 
	public int update(MemberVO vo) {
		logger.info("update() 호출 vo : " + vo);
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public String searchId(String memberName, String memberEmail) {
		logger.info("searchId() 호출 memberName : " + memberName);
		logger.info("searchId() 호출 memberEmail : " + memberEmail);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberName", memberName);
		args.put("memberEmail", memberEmail);
		return sqlSession.selectOne(NAMESPACE + ".search_id", args);
	}

	@Override
	public String searchPw(String memberId, String memberPhone) {
		logger.info("searchPw() 호출 memberId : " + memberId);
		logger.info("searchPw() 호출 memberPhone : " + memberPhone);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberPhone", memberPhone);
		return sqlSession.selectOne(NAMESPACE + ".search_pw", args);
	}

	@Override
	public int updatePw(String memberId, String memberPw) {
		logger.info("updatePw() 호출 memberPw : " + memberPw);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberPw", memberPw);
		return sqlSession.update(NAMESPACE + ".updatePw", args);
	}

	@Override
	public int updateNickname(String memberId, String memberNickname) {
		logger.info("updateNickname() 호출 memberNickname : " + memberNickname);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberNickname", memberNickname);
		return sqlSession.update(NAMESPACE + ".updateNickname", args);
	}

	@Override
	public int updatePhone(String memberId, String memberPhone) {
		logger.info("updatePhone() 호출 memberPhone : " + memberPhone);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberPhone", memberPhone);
		return sqlSession.update(NAMESPACE + ".updatePhone", args);
	}

	@Override
	public int updateEmail(String memberId, String memberEmail) {
		logger.info("updateEmail() 호출 memberEmail : " + memberEmail);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberEmail", memberEmail);
		return sqlSession.update(NAMESPACE + ".updateEmail", args);
	}

	@Override
	public int updateInterest(String memberId, String memberInterest) {
		logger.info("updateInterest() 호출 memberInterest : " + memberInterest);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberInterest", memberInterest);
		return sqlSession.update(NAMESPACE + ".updateInterest", args);

	}

	@Override
	public int updateAddress(String memberId, String memberAddress) {
		logger.info("updateAddress() 호출 memberAddress : " + memberAddress);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberAddress", memberAddress);
		return sqlSession.update(NAMESPACE + ".updateAddress", args);
	}

	
}
