package project.spring.calla.persistence;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import project.spring.calla.domain.MemberVO;

@Repository
public class MemberDAOImple implements MemberDAO{


	private static final Logger logger =
			LoggerFactory.getLogger(FBoardDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.MemberMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int checkId(String memberId) { // ¾ÆÀÌµð Áßº¹Ã¼Å©
		logger.info("select_by_id() È£Ãâ");
		int result  = sqlSession.selectOne(NAMESPACE + ".select_by_id", memberId);
		logger.info(result+"Áßº¹");
		return result;
	}

	@Override
	public int checkNickname(String memberNickname) { // ´Ð³×ÀÓ Áßº¹Ã¼Å©
		logger.info("select_by_nickname() È£Ãâ");
		int result = sqlSession.selectOne(NAMESPACE + ".select_by_nickname", memberNickname);
		logger.info(result+"Áßº¹");
		return result;
	}

	@Override
	public int insert(MemberVO vo) { // È¸¿øÁ¤º¸ µî·Ï
		logger.info("insert() È£Ãâ : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	
	public String login(String memberId, String memberPw) {
		logger.info("login() È£Ãâ memberId = " + memberId + "memberPw = " + memberPw);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("memberId", memberId);
		args.put("memberPw", memberPw);
		return sqlSession.selectOne(NAMESPACE + ".login", args);
	}

	@Override
	public MemberVO select(String memberId) {
		logger.info("select(memberId) È£Ãâ memberId : " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", memberId);
	}

	@Override
	public int update(MemberVO vo) {
		logger.info("update() È£Ãâ vo : " + vo);
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	
}
