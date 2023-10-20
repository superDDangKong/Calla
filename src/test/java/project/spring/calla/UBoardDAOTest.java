package project.spring.calla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.driver.OracleDriver;
import project.spring.calla.domain.UproductVO;
import project.spring.calla.domain.UImageVO;
import project.spring.calla.persistence.UBoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
@WebAppConfiguration

public class UBoardDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(UBoardDAOTest.class);

	@Autowired
	private UBoardDAO dao;

	@Transactional
	@Test
	public void testDAO() {
//		testInsert();
//		testImageInsert();
//		testSelectAll();
//		testSelectByBoardId();
//		testUpdate();
//		testDelete();
//		testSelectPaging();
//		testTotalCounts();
//		testSelectSearch();
	}



	private void testInsert() {
		UproductVO vo = new UproductVO(1, "당근", "2000원", 0, 0, null, "야채", "경기도", "안녕하세요");

		
		int result = dao.insert(vo);
		if (result == 1) {
			logger.info("insert 성공");
		} else {
			logger.info("insert 실패");
		}
		
		
	}

}
