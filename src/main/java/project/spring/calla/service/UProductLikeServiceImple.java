package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.UProductLikeVO;
import project.spring.calla.persistence.UProductDAO;
import project.spring.calla.persistence.UProductLikeDAO;

@Service
public class UProductLikeServiceImple implements UProductLikeService {

	private static final Logger logger =
			LoggerFactory.getLogger(UProductLikeServiceImple.class);
	
	@Autowired
	private UProductLikeDAO uproductlikedao;
	
	@Autowired
	private UProductDAO uproductdao;

	@Transactional(value = "transactionManager")
	@Override
	public int create(UProductLikeVO vo) throws Exception {
		logger.info("create() �샇異� : vo = " + vo.toString());
		int resultInsert = uproductlikedao.insert(vo);
		logger.info(resultInsert + "�꽦怨�");
		int result = uproductdao.updateLikeCount(1, vo.getuProductId());
		logger.info(result + "�꽦怨�");
		return 1;
	}

	@Transactional(value = "transactionManager")
	@Override
	public int delete(int uProductId, String memberId) throws Exception {
		logger.info("delete() �샇異� : productId = " + uProductId + ", memberId = " + memberId);
		int resultDelete = uproductlikedao.delete(uProductId, memberId);
		logger.info(resultDelete + "�꽦怨�");
		int result = uproductdao.updateLikeCount(-1, uProductId);
		logger.info(result + "�꽦怨�");
		return 1;
	}

	@Override
	public int getTotalCounts(int uProductId) {
		logger.info("getTotalCounts() �샇異�");
		return uproductlikedao.getTotalCount(uProductId);
	}

	@Override
	public int checkProductLike(int uProductLikeId, String memberId) {
		logger.info("checkProductLike() �샇異�");
		int result = uproductlikedao.checkProductLike(uProductLikeId, memberId);
		 if (result > 0) { //醫뗭븘�슂瑜� �븳 寃�
		        return 1;
		    } else { //醫뗭븘�슂瑜� �븯吏� �븡�� 寃�
		        return 0;
		    }
	}

	@Override
	public UProductLikeVO read(int uProductId, String memberId) {
		logger.info("read() �샇異� : memberId = " + memberId);
		return uproductlikedao.select(uProductId, memberId);
	}
	
	

}
