package project.spring.calla;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import project.spring.calla.domain.UproductCommentVO;
import project.spring.calla.persistence.UProductCommentDAO;


class UproductCommentDAOTest {

	private static final Logger logger =
			LoggerFactory.getLogger(UproductCommentDAOTest.class);
	
	@Autowired
	private UProductCommentDAO dao;
	
	@Test
	public void testDAO() {
//		testInsert();
//		testSelectAll();
//		testUpdate();
//		testDelte();
	}

	private void testDelte() {
		int result = dao.delete(1);
		logger.info(result + "행 삭제");
		
	}

	private void testUpdate() {
		int result = dao.update(1, "변경");
		logger.info(result + "행 변경");
		
	}

	private void testSelectAll() {
		List<UproductCommentVO> list = dao.select(1);
		logger.info(list.size() + "");
		for(UproductCommentVO vo : list) {
			logger.info(vo.toString());
		}
		
	}

	private void testInsert() {
		UproductCommentVO vo = new UproductCommentVO(0, 1, "test", "반가워요", null, "0");
		int result = dao.insert(vo);
		logger.info(result + "행 삽입");	
	}

}
