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
		logger.info("create() ȣ�� : vo = " + vo.toString());
		int resultInsert = productLikeDAO.insert(vo);
		logger.info(resultInsert + " ���ƿ�");
		int result = productDAO.updateLikeCount(1, vo.getProductId());
		logger.info(result + "���ƿ�");
		return 1;
	}

	@Transactional(value = "transactionManager")
	@Override
	public int delete(int productLikeId, int productId) throws Exception {
		logger.info("delete() ȣ�� : productLikeId = " + productLikeId);
		int resultDelete = productLikeDAO.delete(productLikeId);
		logger.info(resultDelete + "���ƿ� ���");
		int result = productDAO.updateLikeCount(-1, productId);
		logger.info(result + "���ƿ� ���");
		return 1;
	}

	@Override
	public int getTotalCounts(int productId) {
		logger.info("getTotalCounts() ȣ��");
		return productLikeDAO.getTotalCount(productId);
	}

}
