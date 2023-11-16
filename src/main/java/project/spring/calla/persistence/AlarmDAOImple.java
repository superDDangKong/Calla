package project.spring.calla.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.AlarmVO;

@Repository
public class AlarmDAOImple implements AlarmDAO {

	private static final Logger logger =
			LoggerFactory.getLogger(AlarmDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.AlarmMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<AlarmVO> select(String hsid) {
		logger.info("select() »£√‚");
		return sqlSession.selectList(NAMESPACE + ".select", hsid);
	}
	
	
	
}
