package project.spring.calla.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.ProductLikeVO;
import project.spring.calla.persistence.ProductDAO;
import project.spring.calla.persistence.ProductLikeDAO;

@Service
public class ProductLikeServiceImple implements ProductLikeService {
	private static final Logger logger =
			LoggerFactory.getLogger(ProductLikeServiceImple.class);
	
	@Autowired
	private ProductLikeDAO productLikeDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Transactional(value = "transactionManager")
	@Override
	public int create(ProductLikeVO vo) throws Exception {
		logger.info("create() 호출 : vo = " + vo.toString());
		int resultInsert = productLikeDAO.insert(vo);
		logger.info(resultInsert + "성공");
		int result = productDAO.updateLikeCount(1, vo.getProductId());
		logger.info(result + "성공");
		return 1;
	}

	@Transactional(value = "transactionManager")
	@Override
	public int delete(int productId, String memberId) throws Exception {
		logger.info("delete() 호출 : productId = " + productId + ", memberId = " + memberId);
		int resultDelete = productLikeDAO.delete(productId, memberId);
		logger.info(resultDelete + "성공");
		int result = productDAO.updateLikeCount(-1, productId);
		logger.info(result + "성공");
		return 1;
	}

	@Override
	public int getTotalCounts(int productId) {
		logger.info("getTotalCounts() 호출");
		return productLikeDAO.getTotalCount(productId);
	}

	@Override
	public int checkProductLike(int productLikeId, String memberId) {
		logger.info("checkProductLike() 호출");
		int result = productLikeDAO.checkProductLike(productLikeId, memberId);
		 if (result > 0) { //좋아요를 한 것
		        return 1;
		    } else { //좋아요를 하지 않은 것
		        return 0;
		    }
	}

	@Override
	public ProductLikeVO read(int productId, String memberId) {
		logger.info("read() 호출 : memberId = " + memberId);
		return productLikeDAO.select(productId, memberId);
	}

}
