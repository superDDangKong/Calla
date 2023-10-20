package project.spring.calla.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.UBoardVO;
import project.spring.calla.domain.UImageVO;

@Repository // @Component
public class UBoardDAOImple implements UBoardDAO {

	private static final Logger logger = LoggerFactory.getLogger(UBoardDAOImple.class);

	private static final String NAMESPACE = "project.spring.calla.UboardMapper";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(UBoardVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
		// NAMESPACE가 동일한 mapper를 찾아가서 id="insert"인
		// <insert> 태그에 vo 데이터를 전송
	}

	@Override
	public void imageinsert(UImageVO vo) {
		// TODO Auto-generated method stub
		
	}

}
