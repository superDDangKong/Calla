package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.ProductLikeVO;
import project.spring.calla.persistence.ProductLikeDAO;

@Service
public class ProductLikeServiceImple implements ProductLikeService {

	private static final Logger logger =
			LoggerFactory.getLogger(ProductLikeServiceImple.class);
	
	@Autowired
	private ProductLikeDAO dao;
	
	@Override
	public int create(ProductLikeVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProductLikeVO> read(String memberNickname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int productLikeId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
