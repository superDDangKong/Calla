package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.UBoardVO;
import project.spring.calla.domain.UImageVO;
import project.spring.calla.persistence.UBoardDAO;

@Service // @Component
// * 서비스 계층(Service/Business Layer)
// - 표현 계층(Presentation Layer)과 영속 계층(Persistence Layer)사이를
//	 연결하여 두 계층이 직접적으로 통신하지 않도록 하는 역할
// - 트랜잭션(transaction) 관리
// - DB와 상관없이 데이터를 처리 가능
//
public class UBoardServiceImple implements UBoardService {
	private static final Logger logger = LoggerFactory.getLogger(UBoardServiceImple.class);

	@Autowired
	private UBoardDAO dao;

	@Override
	public int create(UBoardVO vo) {
		logger.info("create() 호출 : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public int imagecreate(UImageVO vo) {
		logger.info("imagecreate() 호출 : vo = " + vo.toString());
		return dao.imageinsert(vo);
	}



}
