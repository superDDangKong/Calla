package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UproductVO;
import project.spring.calla.pageutil.PageCriteria;

@Repository
public class UproductDAOImple implements UproductDAO {
	
	private static final Logger logger=
			LoggerFactory.getLogger(UproductDAOImple.class);
	
	private static final String NAMESPACE =
			"project.spring.calla.UboardMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(UproductVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	} // end insert()

	@Override
	public List<UproductVO> select() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	} // end select()

	@Override
	public UproductVO select(int uProductId) {
		logger.info("select() 호출 : uProductId = " + uProductId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_product_id", uProductId);
	}

	@Override
	public int update(UproductVO vo) {
		logger.info("update() 호출 : vo = " + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int uProductId) {
		logger.info("delete() 호출 : productId = " + uProductId);
		return sqlSession.delete(NAMESPACE + ".delete", uProductId);
	}

	@Override
	public List<UproductVO> select(PageCriteria criteria) {
		logger.info("select() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	@Override
	public int getTotalCount() {
		logger.info("getTotalCounts()");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}

	@Override
	public List<UproductVO> select(String uProductName) {
		logger.info("select() 호출 : uProductName = " + uProductName);
		return sqlSession.selectList(NAMESPACE + ".select_by_product_name", "%" + uProductName);
	}

	@Override
	public List<UproductVO> selectByName(String keyword) {
		logger.info("selectByName() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_by_name", "%" + keyword + "%");
	}

	@Override
	public int updateUproductCommentCount(int amount, int uProductId) {
		logger.info("updateProdcutCommentCount() : uProductId = " + uProductId);
		Map<String, Integer> args = new HashMap();
		args.put("amount", amount);
		args.put("uProductId", uProductId);
		return sqlSession.update(NAMESPACE + ".update_product_comment_count", args);
	}

	@Override
	public List<UproductVO> selectAllByMemberNickname(String memberNickname) {
		logger.info("selectAllByMemberNickname() 호출 memberNickname = " + memberNickname);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_memberNickname", memberNickname);
	}

}
