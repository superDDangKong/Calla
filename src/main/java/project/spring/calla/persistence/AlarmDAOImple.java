package project.spring.calla.persistence;

import java.util.ArrayList;
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
	public List<AlarmVO> select(String memberNickname) {
		logger.info("select() 호출 hsid = " + memberNickname);
		return sqlSession.selectList(NAMESPACE + ".select", memberNickname);
	}

	@Override
	public int insert(AlarmVO vo) {
		logger.info("select() 호출 vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int update(int alarmId) {
		logger.info("update() 호출 alarmId = " + alarmId);
		return sqlSession.update(NAMESPACE + ".update", alarmId);
	}

	@Override
	public int check(String memberNickname) {
		logger.info("check() 호출 memberNickname = " + memberNickname);
		return sqlSession.selectOne(NAMESPACE + ".check", memberNickname);
	}

	@Override
	public int delete(int alarmId) {
		logger.info("delete() 호출 alarmId = " + alarmId);
		return sqlSession.delete(NAMESPACE + ".delete", alarmId);
	}
	
	
	
}
