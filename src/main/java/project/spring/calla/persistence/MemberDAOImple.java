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
import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.PageCriteria;

@Repository
public class MemberDAOImple implements MemberDAO{


	private static final Logger logger =
			LoggerFactory.getLogger(MemberDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.MemberMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(MemberVO vo) { // 회占쏙옙占쏙옙占쏙옙
		logger.info("insert() : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}
	
	@Override
	public int checkId(String memberId) { // 占쏙옙占싱듸옙 占쌩븝옙체크
		logger.info("select_by_id() ");
		int result  = sqlSession.selectOne(NAMESPACE + ".select_by_id", memberId);
		logger.info(result+"占쌩븝옙");
		return result;
	}

	@Override
	public int checkNickname(String memberNickname) { //
		logger.info("checkNickname() ");
		return sqlSession.selectOne(NAMESPACE + ".select_by_nickname", memberNickname);
	}

	@Override
	public MemberVO select(String memberId) {
		logger.info("select(memberId) memberId : " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", memberId);
	}
	
	public String login(String memberId, String memberPw) {
		logger.info("login() memberId = " + memberId + "memberPw = " + memberPw);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("memberId", memberId);
		args.put("memberPw", memberPw);
		return sqlSession.selectOne(NAMESPACE + ".login", args);
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
	public int updatePw(String memberId, String memberPw) {
		logger.info("updatePw()  memberPw : " + memberPw);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberPw", memberPw);
		return sqlSession.update(NAMESPACE + ".updatePw", args);
	}

	@Override
	public int updateNickname(String memberId, String memberNickname) {
		logger.info("updateNickname() memberNickname : " + memberNickname);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberNickname", memberNickname);
		return sqlSession.update(NAMESPACE + ".updateNickname", args);
	}

	@Override
	public int updatePhone(String memberId, String memberPhone) {
		logger.info("updatePhone()  memberPhone : " + memberPhone);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberPhone", memberPhone);
		return sqlSession.update(NAMESPACE + ".updatePhone", args);
	}

	@Override
	public int updateEmail(String memberId, String memberEmail) {
		logger.info("updateEmail()  memberEmail : " + memberEmail);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberEmail", memberEmail);
		return sqlSession.update(NAMESPACE + ".updateEmail", args);
	}

	@Override
	public int updateInterest(String memberId, String memberInterest) {
		logger.info("updateInterest()  memberInterest : " + memberInterest);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberInterest", memberInterest);
		return sqlSession.update(NAMESPACE + ".updateInterest", args);

	}

	@Override
	public int updateAddress(String memberId, String memberAddress) {
		logger.info("updateAddress()  memberAddress : " + memberAddress);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberAddress", memberAddress);
		return sqlSession.update(NAMESPACE + ".updateAddress", args);
	}

	@Override
	public List<MemberVO> select() {
		logger.info("select 호출");
		return sqlSession.selectList(NAMESPACE + ".select");
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
	public int delete(String memberId) {
		logger.info("delete()");
		return sqlSession.delete(NAMESPACE + ".delete", memberId);
	}
	


}
