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
	public int checkId(String memberId) { // 아이디 중복체크
		logger.info("select_by_id() 호출");
		int result  = sqlSession.selectOne(NAMESPACE + ".select_by_id", memberId);
		logger.info(result+"중복");
		return result;
	}

	@Override
	public int checkNickname(String memberNickname) { // 닉네임 중복체크
		logger.info("select_by_nickname() 호출");
		int result = sqlSession.selectOne(NAMESPACE + ".select_by_nickname", memberNickname);
		logger.info(result+"중복");
		return result;
	}

	@Override
	public int insert(MemberVO vo) { // 회원가입
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	
	public String login(String memberId, String memberPw) {
		logger.info("login() 호출 memberId = " + memberId + "memberPw = " + memberPw);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("memberId", memberId);
		args.put("memberPw", memberPw);
		return sqlSession.selectOne(NAMESPACE + ".login", args);
	}

	@Override
	public MemberVO select(String memberId) {
		logger.info("select(memberId) 호출 memberId : " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", memberId);
	}

	@Override 
	public int update(MemberVO vo) {
		logger.info("update() 호출 vo : " + vo);
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	
}
