package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.UProductReviewVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.UProductReviewDAO;

@Service
public class UProductReviewServiceImple implements UProductReviewService{
	private static final Logger logger = 
			LoggerFactory.getLogger(UProductReviewServiceImple.class);
	
	@Autowired
	private UProductReviewDAO dao;
	

	@Override
	public int create(UProductReviewVO vo) throws Exception {
		logger.info("create() ȣ�� : vo = " + vo.toString());
		return dao.insert(vo);
	}


	@Override
	public List<UProductReviewVO> read(PageCriteria criteria) {
		logger.info("read() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return dao.select(criteria);
	}


	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() ȣ��");
		return dao.getTotalCount();
	}


	@Override
	public List<UProductReviewVO> readysellNickname(PageCriteria criteria, String sellerNickname) {
		logger.info("readybuyuproduct() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("sellerNickname = " + sellerNickname);
		
		return dao.selectbySellnickname(criteria, sellerNickname);
	}


	@Override
	public int getTotalCountssellNickname(String sellerNickname) {
		logger.info("getTotalCountsbuyuproduct() ȣ��");
		return dao.getTotalCountbySellnickname(sellerNickname);
	}


	@Override
	public UProductReviewVO read(String sellerNickname) {
		logger.info("read() ȣ�� : sellerNickname = " + sellerNickname);
		return dao.select(sellerNickname);
	}


}
