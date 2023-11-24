package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.ProductImageVO;
import project.spring.calla.persistence.ProductImageDAO;

@Service
public class ProductImageServiceImple implements ProductImageService {

	private static final Logger logger = 
			LoggerFactory.getLogger(ProductImageServiceImple.class);
	
	@Autowired
	private ProductImageDAO dao;
	
	@Transactional(value = "transactionManager")
	@Override
	public int create(ProductImageVO vo) throws Exception {
		logger.info("create() 호출 : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public List<ProductImageVO> read(int productImageId) {
		logger.info("read() 호출 : productId" + productImageId);
		return dao.select(productImageId);
	}

	@Override
	public int update(int productImageId, String productImagePath) {
		logger.info("read() 호출 : productImageId = " + productImageId + ", productImagePath = " +productImagePath);
		return dao.update(productImageId, productImagePath);
	}
	
	@Transactional(value = "transactionManager")
	@Override
	public int delete(int productId) throws Exception {
		logger.info("delete() 호출 : productId = " + productId);
		return dao.delete(productId);
	}

}
