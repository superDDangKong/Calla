package project.spring.calla.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.memberVO;

@Repository
public class JoinDAOImple implements JoinDAO{

	
	private static final Logger logger=
			LoggerFactory.getLogger(JoinDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.Membermapper"; // 나중에 매퍼 부분만 수정
	
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
	public int insert(memberVO vo) { // 회원정보 등록
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	
	
}
