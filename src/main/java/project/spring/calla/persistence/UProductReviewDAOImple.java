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
import project.spring.calla.domain.UProductReviewVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;

@Repository
public class UProductReviewDAOImple implements UProductReviewDAO{

	private static final Logger logger =
			LoggerFactory.getLogger(UProductReviewDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.UProductReviewMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(UProductReviewVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<UProductReviewVO> select(PageCriteria criteria) {
		logger.info("select() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".select_all", criteria);
	}

	@Override
	public int getTotalCount() {
		logger.info("getTotalCounts()");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}

	@Override
	public List<UProductReviewVO> selectbySellnickname(PageCriteria criteria, String sellerNickname) {
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		args.put("sellerNickname", sellerNickname);
		logger.info("args = " + args);
		return sqlSession.selectList(NAMESPACE + ".select_sell_all_by_sellnickname", args);
	}

	@Override
	public int getTotalCountbySellnickname(String sellerNickname) {
		logger.info("getTotalCounts()");
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_sellnickname", sellerNickname);
	}

	@Override
	public UProductReviewVO select(String sellerNickname) {
		logger.info("select() 호출 : sellerNickname = " + sellerNickname);
		return sqlSession.selectOne(NAMESPACE + ".select_by_sellernickname", sellerNickname);
	}

	@Override
	public UProductVO selectnickname(String sellerNickname) {
		logger.info("select() 호출 : sellerNickname = " + sellerNickname);
		return sqlSession.selectOne(NAMESPACE + ".select_by_membernickname", sellerNickname);
	}
	
	@Override
	public MemberVO selectmemberManner(String memberNickname) {
		logger.info("select() 호출 : memberNickname = " + memberNickname);
		return sqlSession.selectOne(NAMESPACE + ".select_by_memberManner", memberNickname);
	}

	@Override
	public float updatememberManner(String memberNickname) {
		logger.info("select() 호출 : memberNickname = " + memberNickname);
		return sqlSession.update(NAMESPACE + ".memberManner_update", memberNickname);
	}

	
	

	

	


}
