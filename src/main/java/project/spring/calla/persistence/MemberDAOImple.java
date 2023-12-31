package project.spring.calla.persistence;
import java.util.HashMap;
import java.util.List;
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
	
	public String login(String memberId, String memberPw) {
		logger.info("login() memberId = " + memberId + "memberPw = " + memberPw);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("memberId", memberId);
		args.put("memberPw", memberPw);
		return sqlSession.selectOne(NAMESPACE + ".login", args);
	}
	
	@Override
	public MemberVO select(String memberId) {
		logger.info("select(memberId) memberId : " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", memberId);
	}
	
	@Override
	public String searchId(String memberName, String memberEmail) {
		logger.info("searchId()  memberName : " + memberName);
		logger.info("searchId()  memberEmail : " + memberEmail);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberName", memberName);
		args.put("memberEmail", memberEmail);
		return sqlSession.selectOne(NAMESPACE + ".search_id", args);
	}

	@Override
	public String searchPw(String memberId, String memberPhone) {
		logger.info("searchPw()  memberId : " + memberId);
		logger.info("searchPw()  memberPhone : " + memberPhone);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberPhone", memberPhone);
		return sqlSession.selectOne(NAMESPACE + ".search_pw", args);
	}
	
	@Override
	public int delete(String memberId) {
		logger.info("delete()");
		return sqlSession.delete(NAMESPACE + ".delete", memberId);
	}
	
	@Override
	public List<MemberVO> select() {
		logger.info("select()");
		return sqlSession.selectList(NAMESPACE + ".select");
	}
	
	@Override
	public int insert(MemberVO vo) { 
		logger.info("insert() : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	
	@Override
	public int checkId(String memberId) { 
		logger.info("select_by_id() ");
		int result  = sqlSession.selectOne(NAMESPACE + ".select_by_id", memberId);
		logger.info(result+"ㅎㅇ");
		return result;
	}

	@Override
	public int checkNickname(String memberNickname) { //
		logger.info("checkNickname() ");
		return sqlSession.selectOne(NAMESPACE + ".select_by_nickname", memberNickname);
	}


	@Override
	public int update(String memberId, String newData, String category) {
		logger.info("update() memberId : " + memberId + "newData = " + newData + "category = " + category);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("newData", newData);
		args.put("category", category);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int updatePw(String memberId, String memberPw) {
		logger.info("updatePw()  memberPw : " + memberPw);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberPw", memberPw);
		return sqlSession.update(NAMESPACE + ".updatePw", args);
	}

	@Override
	public int updateLevel(String memberId, int amount) {
		logger.info("updateLevel()");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("memberId", memberId);
		args.put("amount", amount);
		return sqlSession.update(NAMESPACE + ".updateLevel", args);
	}

	@Override
	public int deleteUProduct(int uProductId) {
		logger.info("delete() ");
		return sqlSession.delete(NAMESPACE + ".delete_uproduct", uProductId);
	}

	/*
	 * @Override public int insertMailAuthentication(String memberEmail, String
	 * AuthenticationKey) { // 메일을 전송하면 db에 사용자에 이메일과 인증키를 db에 저장
	 * logger.info("insertMailAuthentication() memberEmail: " + memberEmail +
	 * ", AuthenticationKey: " + AuthenticationKey); Map<String, String> args = new
	 * HashMap<String, String>(); args.put("AuthenticationKey", AuthenticationKey);
	 * args.put("memberEmail", memberEmail); return sqlSession.insert(NAMESPACE +
	 * ".insertMailAuth", args); }
	 */




}
